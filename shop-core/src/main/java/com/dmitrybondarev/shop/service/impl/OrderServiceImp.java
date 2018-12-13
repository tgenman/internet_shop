package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.repository.CartRepository;
import com.dmitrybondarev.shop.repository.OrderRepository;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.service.api.OrderService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.OrderNotFoundException;
import com.dmitrybondarev.shop.util.exception.UserNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

    private CartService cartService;

    private CartRepository cartRepository;

    private UserRepository userRepository;

    private MapperUtil mapperUtil;

    private ProductRepository productRepository;

    public OrderServiceImp(OrderRepository orderRepository, CartService cartService, CartRepository cartRepository, UserRepository userRepository, MapperUtil mapperUtil, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.productRepository = productRepository;
    }

    @Override
    @Loggable
    @Transactional
    public void createOrder(OrderDto orderDto) {
        orderDto.setListOfProducts(
                orderDto.getUser().getCartDto().getContent());

        Order order = mapperUtil.mapOrderDtoToOrder(orderDto);

        order.setDateOfOrder(new Date());
        order.setStatusOfDelivery(StatusOfDelivery.WAITING_FOR_PAYMENT);
        order.setStatusOfPayment(StatusOfPayment.WAITING_FOR_PAYMENT);
        order.setBill(this.createBill(orderDto.getUser().getCartDto().getContent()));
        order.setTotal(this.calculateTotal(orderDto.getUser().getCartDto().getContent()));


        Cart cartByUserEmail = cartService.getCartByUserEmail(orderDto.getUser().getEmail());
        Map<Product, Integer> products = cartByUserEmail.getProducts();
        order.setListOfProducts(products);
        orderRepository.save(order);

        cartByUserEmail.setProducts(new HashMap<>());
        cartRepository.save(cartByUserEmail);

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = productRepository.findById(entry.getKey().getId()).get();
            product.setQuantity(product.getQuantity() - entry.getValue());
            productRepository.save(product);
        }

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
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("Not found user with email: " + email);

        User user = optionalUser.get();
        List<Order> allByUser = orderRepository.findAllByUser(user);
        return allByUser.stream().map(order -> mapperUtil.mapOrderToOrderDto(order)).collect(Collectors.toList());
    }

    @Override
    @Loggable
    @Transactional
    public OrderDto getOrderDtoById(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) throw new OrderNotFoundException("No Order found with id: "+ orderId);
        Order order = optionalOrder.get();
        return mapperUtil.mapOrderToOrderDto(order);
    }

    @Override
    @Loggable
    @Transactional
    public void editOrder(OrderDto orderDto) {
        throw new UnsupportedOperationException("public void editOrder(OrderDto orderDto) {");

//        TODO IMPLEMENT
    }

    @Loggable
    private String createBill(Map<ProductDto, Integer> listOfProducts) {
        String result = "";
        for (Map.Entry<ProductDto, Integer> entry : listOfProducts.entrySet()) {
            result = result + "|"+ entry.getKey().getTitle() + ":" + entry.getValue() + "x" + entry.getKey().getPrice();
        }
        return result;
    }

    @Loggable
    private int calculateTotal(Map<ProductDto, Integer> listOfProducts) {
        int result = 0;
        for (Map.Entry<ProductDto, Integer> entry : listOfProducts.entrySet()) {
            result += entry.getKey().getPrice() * entry.getValue();
        }
        return result;
    }

}
