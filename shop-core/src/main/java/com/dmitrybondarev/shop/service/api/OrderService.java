package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrderDto();
}
