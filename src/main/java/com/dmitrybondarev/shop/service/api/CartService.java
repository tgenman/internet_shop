package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Product;

import java.util.Map;

public interface CartService {
    Map<Product, Integer> getCartByUserId(long idUser);

    void addProduct(long idUser, long idProduct);

    void deleteProduct(long idUser, long idProduct);
}
