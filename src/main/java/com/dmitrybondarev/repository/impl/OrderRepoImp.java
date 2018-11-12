package com.dmitrybondarev.repository.impl;

import com.dmitrybondarev.model.Order;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.repository.api.OrderRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Log4j
@Repository
public class OrderRepoImp implements OrderRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveOrder(Order order) {
        log.info("saveOrder start.");
        entityManager.persist(order);
        log.info("saveOrder. Successful");
        return;
    }

    @Override
    public List<Order> findAll() {
        log.info("Finding all. Starting");
        List<Order> orders = entityManager.createQuery("select o from  Order o", Order.class).getResultList();
        log.info("Finding all. end");
        return orders;
    }

}
