package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.dto.AddressDto;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private CartService cartService;

    private UserService userService;

    @Autowired
    private MapperUtil mapperUtil;

    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @ModelAttribute("allTypesOfDelivery")
    public List<TypeOfDelivery> deliveryTypes() {
        return Arrays.asList(TypeOfDelivery.values());
    }

    @ModelAttribute("allTypesOfPayment")
    public List<TypeOfPayment> paymentTypes() {
        return Arrays.asList(TypeOfPayment.values());
    }

    @Loggable
    @GetMapping("/new")
    public String showCreationOrderForm(@AuthenticationPrincipal UserDetails userDetails,
                                        Model model) {


        UserDto userDto = userService.getUserDtoByEmail(userDetails.getUsername());

        Cart cart = cartService.getCartByUserEmail(userDetails.getUsername());
        Set<AddressDto> allAddress = userDto.getAddressDtos();

        model.addAttribute("user", userDto);         //TODO Change to DTOs
        model.addAttribute("allAddress", allAddress);
        model.addAttribute("cart", cart);
        model.addAttribute("orderDto", new OrderDto());

        return "order/newOrder";
    }

    @Loggable
    @PostMapping("/new")
    public String createNewOrder(@ModelAttribute("orderDto") @Valid OrderDto orderDto,
                                       BindingResult result, Errors errors,
                                       @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (!result.hasErrors()) {

            UserDto userDto = userService.getUserDtoByEmail(userDetails.getUsername());

            orderDto.setUserDto(userDto);
            orderDto.setDateOfOrder(new Date());
            orderDto.setStatusOfDelivery(StatusOfDelivery.WAITING_FOR_PAYMENT);
            orderDto.setStatusOfPayment(StatusOfPayment.WAITING_FOR_PAYMENT);

            orderService.createOrder(orderDto);

            return "redirect:/";
        }
        model.addAttribute("orderDto", orderDto);
        return "order/newOrder";
    }

//    @GetMapping("/list")   TODO Implement showListOfOrderForUser
//    public ModelAndView showListOfOrderForUser() {
//        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//
//
//        List<OrderDto> allOrderDto = orderService.getAllOrderDto();
//        ModelAndView view = new ModelAndView("/admin/order/orderList.jsp", "orderDtos", allOrderDto);
//        return view;
//    }

}
