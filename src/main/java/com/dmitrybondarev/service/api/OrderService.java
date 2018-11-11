package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.dto.OrderDto;

public interface OrderService {
    void createOrder(OrderDto orderDto);
}
