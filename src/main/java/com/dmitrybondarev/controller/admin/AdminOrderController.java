package com.dmitrybondarev.controller.admin;

import com.dmitrybondarev.model.dto.OrderDto;
import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.OrderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Log4j
@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ModelAndView showListOfOrders() {
        log.info("showListOfOrders");
        List<OrderDto> allOrderDto = orderService.getAllOrderDto();
        ModelAndView view = new ModelAndView("/admin/order/orderList.jsp", "orderDtos", allOrderDto);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView showOrderEditForm(@PathVariable long id) { //TODO Implement showOrderEditForm
        log.info("showOrderEditForm start");

        log.info("showOrderEditForm end");
        return new ModelAndView("/home.jsp");
    }

    @PostMapping("/{id}")
    public ModelAndView editOrder(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                  BindingResult result, Errors errors) { //TODO Implement editOrder
        log.info("editOrder start");

        log.info("editOrder end");
        return new ModelAndView("/home.jsp");
    }
}
