package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.util.exception.CategoryExistsException;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategoryDto();

    void addNewCategory(CategoryDto categoryDto) throws CategoryExistsException;

    CategoryDto getCategoryDtoById(long categoryId);

    void editCategory(CategoryDto categoryDto);
}
