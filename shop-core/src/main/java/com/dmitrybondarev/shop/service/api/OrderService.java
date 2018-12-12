package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrderDto();

    List<OrderDto> getAllOrderDtoByUserEmail(String email);

    void createOrder(OrderDto orderDto);

}
