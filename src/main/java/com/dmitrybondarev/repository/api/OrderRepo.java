package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.Order;

import java.util.List;

public interface OrderRepo {
    void saveOrder(Order order);

    List<Order> findAll();
}
