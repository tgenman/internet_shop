package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.Order;

public interface OrderRepo {
    void saveOrder(Order order);

}
