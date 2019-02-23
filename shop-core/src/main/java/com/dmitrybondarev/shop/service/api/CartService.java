package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.util.exception.ProductNotFoundException;
import com.dmitrybondarev.shop.util.exception.UserNotFoundException;

public interface CartService {

    Cart getCartByUserEmail(String emailUser, String cartUID) throws UserNotFoundException;

    Cart getCartByByCardUID(String sessionId);

    void modificationCartByUserEmail(String userEmail, long productId, int amount, String cardUID) throws UserNotFoundException, ProductNotFoundException;

    void modificationCartByCartId(String cartUID, long productId, int amount) throws ProductNotFoundException;
}
