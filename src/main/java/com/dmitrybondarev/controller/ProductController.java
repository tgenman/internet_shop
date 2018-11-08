package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/all")
    public ModelAndView showProductsForUser() {
        log.info("ProductsForUser GET request");
        return new ModelAndView("product/all", "productDtos", productService.getProductsFromStock());
    }

    @GetMapping("/inventory") //TODO Move to StoreController. Maybe
    public ModelAndView showInventory() {
        log.info("Inventory GET request");
        return new ModelAndView("store/inventory", "productDtos", productService.getAllProducts());
    }

    @GetMapping("/new")
    public ModelAndView showProductCreationForm() {
        log.info("ProductCreation GET request");
        return new ModelAndView("product/new", "productDto", new ProductDto());
    }



}
