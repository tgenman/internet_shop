package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Category;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByActiveTrueAndCategoryAndQuantityGreaterThan(Category category, int amount);

    List<Product> findAllByActiveTrueAndQuantityGreaterThan(int amount);

    List<Product> findAllByAdvertisingTrueAndActiveTrue();

    Optional<Product> findByTitleAndBrand(String title, String brand);
}
