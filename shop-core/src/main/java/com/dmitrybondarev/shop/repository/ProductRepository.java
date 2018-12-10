package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);

    List<Product> findAllByActiveTrueAndQuantityGreaterThan(int amount);

}
