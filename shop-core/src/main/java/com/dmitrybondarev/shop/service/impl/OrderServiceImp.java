package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.repository.OrderRepository;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Loggable
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Order order = this.mapOrderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    @Override
    @Loggable
    @Transactional
    public List<OrderDto> getAllOrderDto() {
        Iterable<Order> all = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : all) {
            orderDtos.add(this.mapOrderToOrderDto(order));
        }
        return orderDtos;
    }



    // ============== NON-API ============

    private OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setDateOfOrder(order.getDateOfOrder());
        orderDto.setListOfProducts(order.getListOfProducts());
        orderDto.setStatusOfDelivery(order.getStatusOfDelivery());
        orderDto.setStatusOfPayment(order.getStatusOfPayment());
        orderDto.setTypeOfDelivery(order.getTypeOfDelivery());
        orderDto.setTypeOfPayment(order.getTypeOfPayment());
        orderDto.setUser(order.getUser());
        return orderDto;
    }

    private Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(null);
        order.setAddress(orderDto.getAddress());
        order.setDateOfOrder(orderDto.getDateOfOrder());
        order.setListOfProducts(orderDto.getListOfProducts());
        order.setStatusOfDelivery(orderDto.getStatusOfDelivery());
        order.setStatusOfPayment(orderDto.getStatusOfPayment());
        order.setTypeOfDelivery(orderDto.getTypeOfDelivery());
        order.setTypeOfPayment(orderDto.getTypeOfPayment());
        order.setUser(orderDto.getUser());
        return order;
    }
}
