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
        order.setId(orderDto.getId());
        order.setAddress(orderDto.getAddress());
        order.setDateOfOrder(orderDto.getDateOfOrder());
        order.setListOfProducts(orderDto.getListOfProducts());
        order.setStatusOfDelivery(orderDto.getStatusOfDelivery());
        order.setStatusOfPayment(orderDto.getStatusOfPayment());
        order.setTypeOfDelivery(orderDto.getTypeOfDelivery());
        order.setTypeOfPayment(orderDto.getTypeOfPayment());
        order.setUser(orderDto.getUser());
        return order;
    }
}
