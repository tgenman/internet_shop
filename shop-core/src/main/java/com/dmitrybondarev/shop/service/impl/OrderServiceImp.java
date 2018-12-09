package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.repository.OrderRepository;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

    private MapperUtil mapperUtil;

    public OrderServiceImp(OrderRepository orderRepository, MapperUtil mapperUtil) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Order order = mapperUtil.mapOrderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    @Override
    @Loggable
    @Transactional
    public List<OrderDto> getAllOrderDto() {
        Iterable<Order> all = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : all) {
            orderDtos.add(mapperUtil.mapOrderToOrderDto(order));
        }
        return orderDtos;
    }
}
