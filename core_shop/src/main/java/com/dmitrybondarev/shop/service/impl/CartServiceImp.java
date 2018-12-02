package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImp implements CartService {

    private UserRepo userRepo;

    private ProductRepo productRepo;

    public CartServiceImp(UserRepo userRepo, ProductRepo productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    @Loggable
    @Transactional
    public Map<Product, Integer> getCartByUserId(long idUser) {
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            cart = new HashMap<>();
            user.setCart(cart);
            userRepo.update(user);
        }
        return cart;
    }

    @Override
    @Loggable
    @Transactional
    public void addProduct(long idUser, long idProduct) {
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if(cart == null) {
            cart = new HashMap<>();
        }
        cart.put(product, 1);
        userRepo.update(user);
    }

    @Override
    @Loggable
    @Transactional
    public void deleteProduct(long idUser, long idProduct) {
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            return;
        }
        cart.remove(product);
        userRepo.update(user);
    }
}
