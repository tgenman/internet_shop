package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.CartDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/order")
@SessionAttributes("idCard")
public class OrderController {

    private OrderService orderService;

    private CartService cartService;

    private UserService userService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Loggable
    @GetMapping
    public String showListOfOrderForUser(@AuthenticationPrincipal UserDetails userDetails,
                                         Model model) {
        List<OrderDto> orderDtos =
                orderService.getAllOrderDtoByUserEmail(userDetails.getUsername());
        model.addAttribute("orderDtos", orderDtos);

        return "/order/OrderListForUser";
    }

    @Loggable
    @GetMapping("/new")
    public String showCreationOrderForm(@AuthenticationPrincipal UserDetails userDetails,
                                        Model model) {

        UserDto userDto = userService.getUserDtoByEmail(userDetails.getUsername());

        Cart cart = cartService.getCartByUserEmail(userDetails.getUsername());
        Set<AddressDto> allAddressDtos = userDto.getAddresses();

        model.addAttribute("sum", this.countSum(cart));
        model.addAttribute("cart", cart);
        model.addAttribute("userDto", userDto);
        model.addAttribute("allAddressDtos", allAddressDtos);
        model.addAttribute("allTypesOfPayment", Arrays.asList(TypeOfPayment.values()));
        model.addAttribute("allTypesOfDeliver", Arrays.asList(TypeOfDelivery.values()));
        model.addAttribute("orderDto", new OrderDto());

        return "order/newOrder";
    }

    @Loggable
    @PostMapping("/new")
    public String createNewOrder(OrderDto orderDto,
                                 BindingResult result, Errors errors,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderDto", orderDto);
            return "order/newOrder";
        }

        orderDto.setUser(
                userService.getUserDtoByEmail(
                        userDetails.getUsername()));

        orderService.createOrder(orderDto);
        return "redirect:/";
    }

//  =============== NON_API =================


    private int countSum(Cart cart) {
        int sum = 0;
        Map<Product, Integer> products = cart.getProducts();
        for (Map.Entry<Product, Integer> entries : products.entrySet()) {
            int price = entries.getKey().getPrice();
            int amount = entries.getValue();
            sum += price * amount;
        }
        return sum;
    }
}
