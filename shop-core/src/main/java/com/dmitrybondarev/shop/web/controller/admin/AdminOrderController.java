package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.util.logging.Loggable;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.service.api.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController {

    private OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("allStatusesOfDelivery")
    public List<StatusOfDelivery> deliveryStatuses() {
        return Arrays.asList(StatusOfDelivery.values());
    }

    @ModelAttribute("allStatusesOfPayment")
    public List<StatusOfPayment> paymentStatuses() {
        return Arrays.asList(StatusOfPayment.values());
    }

    @Loggable
    @GetMapping
    public String showListOfOrders(Model model) {
        model.addAttribute("allOrderDto", orderService.getAllOrderDto());
        return "/admin/order/orderList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showOrderEditForm(@PathVariable long id) { //TODO Implement showOrderEditForm

        return "/";
    }

    @Loggable
    @PostMapping("/{id}")
    public String editOrder(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                  BindingResult result, Errors errors) { //TODO Implement editOrder

        return "redirect:/admin/order";
    }
}
