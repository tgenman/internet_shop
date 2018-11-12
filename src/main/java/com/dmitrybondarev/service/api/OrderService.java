package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrderDto();
}
