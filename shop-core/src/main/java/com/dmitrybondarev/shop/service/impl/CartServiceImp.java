package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImp implements CartService {

    private UserRepository userRepository;

    private ProductRepository productRepository;

    public CartServiceImp(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Loggable
    @Transactional
    public Map<Product, Integer> getCartByUserId(long idUser) {
        User user = userRepository.findById(idUser).get();
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            cart = new HashMap<>();
            user.setCart(cart);
            userRepository.save(user);
        }
        return cart;
    }

    @Override
    @Loggable
    @Transactional
    public void addProduct(long idUser, long idProduct) {
        Product product = productRepository.findById(idProduct).get();
        User user = userRepository.findById(idUser).get();
        Map<Product, Integer> cart = user.getCart();
        if(cart == null) {
            cart = new HashMap<>();
        }
        cart.put(product, 1);
        userRepository.save(user);
    }

    @Override
    @Loggable
    @Transactional
    public void deleteProduct(long idUser, long idProduct) {
        Product product = productRepository.findById(idProduct).get();
        User user = userRepository.findById(idUser).get();
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            return;
        }
        cart.remove(product);
        userRepository.save(user);
    }
}
