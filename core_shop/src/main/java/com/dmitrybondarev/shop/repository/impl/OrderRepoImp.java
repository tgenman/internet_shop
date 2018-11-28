package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.repository.api.OrderRepo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepoImp extends GenericRepoImpl<Order> implements OrderRepo {

    public OrderRepoImp() {
        setClazz(Order.class);
    }

    @Override
    public void delete(Order entity) {
        throw new UnsupportedOperationException("You cant delete Order from DB. Use inactivation");
    }

    @Override
    public void deleteById(Long entityId) {
        throw new UnsupportedOperationException("You cant delete Order from DB. Use inactivation");
    }
}
