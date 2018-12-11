package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(String filter);

    List<Product> findAllByActiveTrueAndQuantityGreaterThan(int amount);

    Optional<Product> findByTitleAndBrand(String title, String brand);

}
