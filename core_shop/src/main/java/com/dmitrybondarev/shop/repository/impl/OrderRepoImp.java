package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.repository.api.OrderRepo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepoImp extends GenericRepoImpl<Order> implements OrderRepo {

    public OrderRepoImp() {
        setClazz(Order.class);
    }
}
