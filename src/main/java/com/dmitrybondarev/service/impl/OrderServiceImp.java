package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Order;
import com.dmitrybondarev.model.dto.OrderDto;
import com.dmitrybondarev.repository.api.OrderRepo;
import com.dmitrybondarev.service.api.OrderService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        log.info("createOrder");
        Order order = this.mapOrderDtoToOrder(orderDto);
        orderRepo.saveOrder(order);
    }



// ============== NON-API ============

    private OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        mapper.map(order, orderDto);
        return orderDto;
    }

    private Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        mapper.map(orderDto, order);
        return order;
    }
}
