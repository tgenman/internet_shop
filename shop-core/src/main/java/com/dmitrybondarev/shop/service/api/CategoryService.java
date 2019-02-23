package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.util.exception.CategoryExistsException;
import com.dmitrybondarev.shop.util.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategoryDto();

    void addNewCategory(CategoryDto categoryDto) throws CategoryExistsException;

    CategoryDto getCategoryDtoById(long categoryId) throws CategoryNotFoundException;

    void editCategory(CategoryDto categoryDto) throws CategoryNotFoundException;
}
