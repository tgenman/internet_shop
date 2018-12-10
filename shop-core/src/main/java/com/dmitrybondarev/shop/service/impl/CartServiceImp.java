package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Cart getCartByUserId(long idUser) {
        User user = userRepository.findById(idUser).get();
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
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
        Cart cart = user.getCart();
        if(cart == null) {
            cart = new Cart();
        }
        cart.getProducts().put(product, 1);
        userRepository.save(user);
    }

    @Override
    @Loggable
    @Transactional
    public void deleteProduct(long idUser, long idProduct) {
        Product product = productRepository.findById(idProduct).get();
        User user = userRepository.findById(idUser).get();
        Cart cart = user.getCart();
        if (cart == null) {
            return;
        }
        cart.getProducts().remove(product);
        userRepository.save(user);
    }
}
