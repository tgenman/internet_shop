package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.CategoryService;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.exception.CategoryNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Loggable
    @GetMapping
    public String showProductList(@RequestParam(required = false, defaultValue = "") String filter,
                                  Model model) {

        Map<CategoryDto, List<ProductDto>> productDtos;
        try {
            productDtos = productService.getProductDtosFromStockByFilter(filter);
        } catch (CategoryNotFoundException e) {
            productDtos = new HashMap<>();
        }
        model.addAttribute("allProductDto", productDtos);

        List<CategoryDto> allCategoryDto = categoryService.getAllCategoryDto();
        model.addAttribute("allCategoryDto", allCategoryDto);
        return "product/productList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showProduct(@PathVariable long id, Model model) {
        model.addAttribute("productDto", productService.getProductById(id));
        return "/product/productInfo";
    }
}
