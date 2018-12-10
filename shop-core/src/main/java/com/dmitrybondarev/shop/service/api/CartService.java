package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Cart;

public interface CartService {

    Cart getCartByUserId(long idUser);

    void addProduct(long idUser, long idProduct);

    void deleteProduct(long idUser, long idProduct);
}
