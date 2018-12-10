package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
