package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Cart;

public interface CartService {

    Cart getCartByUserEmail(String emailUser);

    Cart getCartByBySessionId(String sessionId);

    void modificationCartByUserEmail(String userEmail, long productId, int amount);

    void modificationCartBySessionId(String sessionId, long productId, int amount);
}
