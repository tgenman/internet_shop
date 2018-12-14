package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.dto.CartDto;

public interface CartService {

    Cart getCartByUserEmail(String emailUser, String cartUID);

    Cart getCartByByCardUID(String sessionId);

    void modificationCartByUserEmail(String userEmail, long productId, int amount, String cardUID);

    void modificationCartByCartId(String cartUID, long productId, int amount);
}
