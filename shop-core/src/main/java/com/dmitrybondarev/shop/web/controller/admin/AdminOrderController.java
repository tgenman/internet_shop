package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    private OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Loggable
    @GetMapping
    public String showListOfOrders(Model model) {
        model.addAttribute("allOrderDto", orderService.getAllOrderDto());
        return "/admin/order/orderList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showOrderEditForm(@PathVariable long id,
                                    Model model,
                                    HttpSession httpSession) {
        httpSession.removeAttribute("idOrderForEdit");
        httpSession.setAttribute("idOrderForEdit", id);

        OrderDto orderDto = orderService.getOrderDtoById(id);
        model.addAttribute("orderDto", orderDto);
        return "/admin/order/orderEdit";
    }

    @Loggable
    @PostMapping
    public String editOrder(@Valid OrderDto orderDto,
                            BindingResult result,
                            HttpServletRequest request,
                            Model model) {
        long idOrderForEdit = (Long) request.getSession().getAttribute("idOrderForEdit");
        orderDto.setId(idOrderForEdit);

        if (result.hasErrors()) {
            model.addAttribute("orderDto", orderDto);
            return "/admin/category/"+ orderDto.getId();
        }

        orderService.editOrder(orderDto);
        return "redirect:/admin/order";
    }
}
