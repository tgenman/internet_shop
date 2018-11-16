package com.dmitrybondarev.shop.controller.admin;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    @Loggable
    public ModelAndView showListOfOrders() {
        List<OrderDto> allOrderDto = orderService.getAllOrderDto();
        ModelAndView view = new ModelAndView("/admin/order/orderList.jsp", "orderDtos", allOrderDto);
        return view;
    }

    @GetMapping("/{id}")
    @Loggable
    public ModelAndView showOrderEditForm(@PathVariable long id) { //TODO Implement showOrderEditForm

        return new ModelAndView("/home.jsp");
    }

    @PostMapping("/{id}")
    @Loggable
    public ModelAndView editOrder(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                  BindingResult result, Errors errors) { //TODO Implement editOrder

        return new ModelAndView("/home.jsp");
    }
}
