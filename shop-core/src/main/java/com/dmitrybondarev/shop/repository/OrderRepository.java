package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
