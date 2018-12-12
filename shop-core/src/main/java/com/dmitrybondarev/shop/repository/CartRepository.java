package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findBySessionId(String sessionId);

}
