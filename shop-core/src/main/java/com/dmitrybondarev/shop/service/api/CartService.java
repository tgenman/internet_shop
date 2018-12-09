package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;

import java.util.Map;

public interface CartService {
    Cart getCartByUserId(long idUser);

    void addProduct(long idUser, long idProduct);

    void deleteProduct(long idUser, long idProduct);
}
