package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.OrderDto;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.StatusOfDelivery;
import com.dmitrybondarev.model.enums.StatusOfPayment;
import com.dmitrybondarev.model.enums.TypeOfDelivery;
import com.dmitrybondarev.model.enums.TypeOfPayment;
import com.dmitrybondarev.service.api.CartService;
import com.dmitrybondarev.service.api.OrderService;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

import static com.dmitrybondarev.model.enums.StatusOfDelivery.WAITING_FOR_PAYMENT;

@Log4j
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
    public ModelAndView showCreationOrderForm(HttpServletRequest request) {
        log.info("showCreationOrderForm start");
        ModelAndView mAV = new ModelAndView("order/newOrder.jsp");

        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDto user = userService.getUserDtoById(idUser);
        request.getSession().setAttribute("userR", user);


        Map<Product, Integer> cart = cartService.getCartByUserId(idUser);
        Set<Address> addresses = user.getAddresses();
        request.getSession().setAttribute("cartR", cart);


        mAV.addObject("TypeOfDelivery", TypeOfDelivery.values());
        mAV.addObject("TypeOfPayment", TypeOfPayment.values());
        mAV.addObject("user", user);
        mAV.addObject("addresses", addresses);
        mAV.addObject("cart", cart);

        log.info("showCreationOrderForm end");
        return mAV;
    }

    @PostMapping("/new")
    public ModelAndView createNewOrder(@ModelAttribute("orderDto") @Valid OrderDto orderDto,
                                            BindingResult result, HttpServletRequest request, Errors errors) {
        log.info("createNewOrder");
        if (!result.hasErrors()) {
            log.info("There are not errors. Start createNewOrder");
            User userR = (User) request.getSession().getAttribute("userR");
            Map<Product, Integer> cartR = (Map<Product, Integer>) request.getSession().getAttribute("cartR");

            orderDto.setUser(userR);
            orderDto.setListOfProducts(cartR);
            orderDto.setDateOfOrder("today");
            orderDto.setStatusOfDelivery(StatusOfDelivery.WAITING_FOR_PAYMENT);
            orderDto.setStatusOfPayment(StatusOfPayment.WAITING_FOR_PAYMENT);

            orderService.createOrder(orderDto);

            log.info("createNewOrder complete.");
            return new ModelAndView("redirect:/");
        }

        log.info("There is error");
        return new ModelAndView("order/newOrder.jsp", "user", orderDto);
    }
}
