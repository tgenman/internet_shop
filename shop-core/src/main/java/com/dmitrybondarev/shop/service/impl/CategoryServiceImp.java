package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Category;
import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.repository.CategoryRepository;
import com.dmitrybondarev.shop.service.api.CategoryService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.CategoryExistsException;
import com.dmitrybondarev.shop.util.exception.CategoryNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;

    private MapperUtil mapperUtil;

    public CategoryServiceImp(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public List<CategoryDto> getAllCategoryDto() {
        List<Category> allCategory = categoryRepository.findAll();
        return allCategory.stream()
                .map(category -> mapperUtil.mapCategoryToCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    @Loggable
    @Transactional
    public CategoryDto getCategoryDtoById(long categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("No category found with id: "+ categoryId));
        return mapperUtil.mapCategoryToCategoryDto(category);
    }

    @Override
    @Loggable
    @Transactional
    public void addNewCategory(CategoryDto categoryDto) throws CategoryExistsException {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryNameContains(categoryDto.getCategoryName());
        if (optionalCategory.isPresent()) throw new CategoryExistsException("There is a category with that name: " + categoryDto.getCategoryName());

        Category category = mapperUtil.mapCategoryDtoToCategory(categoryDto);
        category.setId(null);
        categoryRepository.save(category);
    }

    @Override
    @Loggable
    @Transactional
    public void editCategory(CategoryDto categoryDto) throws CategoryNotFoundException {
        categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new CategoryNotFoundException("No category found with id: " + categoryDto.getId()));

        Category category = mapperUtil.mapCategoryDtoToCategory(categoryDto);
        categoryRepository.save(category);
    }
}
