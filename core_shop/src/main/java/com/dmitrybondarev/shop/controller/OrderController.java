package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.Address;
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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    @Loggable
    public ModelAndView showCreationOrderForm(HttpServletRequest request) {
        ModelAndView mAV = new ModelAndView("order/newOrder.jsp");

        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDto user = userService.getUserDtoById(idUser);
        request.getSession().setAttribute("userR", user);


        Map<Product, Integer> cart = cartService.getCartByUserId(idUser);
        Set<Address> addresses = user.getAddresses();
        request.getSession().setAttribute("cartR", cart);


        mAV.addObject("TypeOfDeliveryL", TypeOfDelivery.values());
        mAV.addObject("TypeOfPaymentL", TypeOfPayment.values());
        mAV.addObject("user", user);
        mAV.addObject("addresses", addresses);
        mAV.addObject("cart", cart);
        mAV.addObject("orderDto", new OrderDto());

        return mAV;
    }

    @PostMapping("/new")
    @Loggable
    public ModelAndView createNewOrder(@ModelAttribute("orderDto") @Valid OrderDto orderDto,
                                       BindingResult result, HttpServletRequest request, Errors errors,
                                       @AuthenticationPrincipal User user) {
        if (!result.hasErrors()) {
            UserDto userDtoR = (UserDto) request.getSession().getAttribute("userR");

            User userR = userService.mapUserDtoToUser(userDtoR);

            Map<Product, Integer> cartR = (Map<Product, Integer>) request.getSession().getAttribute("cartR");

            orderDto.setUser(userR);
            orderDto.setListOfProducts(cartR);
            orderDto.setDateOfOrder("today");
            orderDto.setStatusOfDelivery(StatusOfDelivery.WAITING_FOR_PAYMENT);
            orderDto.setStatusOfPayment(StatusOfPayment.WAITING_FOR_PAYMENT);

            orderService.createOrder(orderDto);

            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("order/newOrder.jsp", "user", orderDto);
    }

//    @GetMapping("/list")
//    public ModelAndView showListOfOrderForUser() {
//        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//
//
//        List<OrderDto> allOrderDto = orderService.getAllOrderDto();
//        ModelAndView view = new ModelAndView("/admin/order/orderList.jsp", "orderDtos", allOrderDto);
//        return view;
//    }

}
