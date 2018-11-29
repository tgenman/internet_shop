package com.dmitrybondarev.shop.controller.admin;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Loggable
    @GetMapping
    public String showListOfOrders(Model model) {
        List<OrderDto> allOrderDto = orderService.getAllOrderDto();
        model.addAttribute("orderDtos", allOrderDto);
        return "/admin/order/orderList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showOrderEditForm(@PathVariable long id) { //TODO Implement showOrderEditForm

        return "/home.jsp";
    }

    @Loggable
    @PostMapping("/{id}")
    public String editOrder(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                  BindingResult result, Errors errors) { //TODO Implement editOrder

        return "redirect:/admin/order";
    }
}
