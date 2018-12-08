package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
