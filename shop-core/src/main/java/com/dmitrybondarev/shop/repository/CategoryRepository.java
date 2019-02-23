package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameContains(String categoryName);

}
