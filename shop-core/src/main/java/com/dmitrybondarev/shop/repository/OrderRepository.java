package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    List<Order> findByStatusOfDeliveryContains(String status);

    List<Order> findAllByTotalGreaterThan(int total);

}
