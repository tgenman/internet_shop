package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.repository.api.OrderRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j
@Repository
public class OrderRepoImp extends GenericRepoImpl<Order> implements OrderRepo {

    public OrderRepoImp() {
        setClazz(Order.class);
    }
}
