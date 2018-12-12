package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.repository.CartRepository;
import com.dmitrybondarev.shop.repository.OrderRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.service.api.UserService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

    private CartService cartService;

    private CartRepository cartRepository;

    private UserRepository userRepository;

    private MapperUtil mapperUtil;


    public OrderServiceImp(OrderRepository orderRepository, CartService cartService, CartRepository cartRepository, UserRepository userRepository, MapperUtil mapperUtil) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public void createOrder(OrderDto orderDto) {
        orderDto.setDateOfOrder(new Date());
        orderDto.setStatusOfDelivery(StatusOfDelivery.WAITING_FOR_PAYMENT);
        orderDto.setStatusOfPayment(StatusOfPayment.WAITING_FOR_PAYMENT);
        Order order = mapperUtil.mapOrderDtoToOrder(orderDto);

        Cart cartByUserEmail = cartService.getCartByUserEmail(orderDto.getUserDto().getEmail());
        order.setListOfProducts(cartByUserEmail.getProducts());
        orderRepository.save(order);

        cartByUserEmail.setProducts(new HashMap<>());
        cartRepository.save(cartByUserEmail);
    }

    @Override
    @Loggable
    @Transactional
    public List<OrderDto> getAllOrderDto() {
        Iterable<Order> all = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : all) {
            orderDtos.add(mapperUtil.mapOrderToOrderDto(order));
        }
        return orderDtos;
    }

    @Override
    @Loggable
    @Transactional
    public List<OrderDto> getAllOrderDtoByUserEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        List<Order> allByUser = orderRepository.findAllByUser(user);
        return allByUser.stream().map(order -> mapperUtil.mapOrderToOrderDto(order)).collect(Collectors.toList());
    }
}
