package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface CartService {
    Map<Product, Integer> getCartByUserId(long idUser);

    void addProduct(long idUser, long idProduct);

    void deleteProduct(long idUser, long idProduct);
}
