package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.service.api.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("allProductDto", productService.getProductsFromStock());
        return "product/productList";
    }

    @Loggable
    @GetMapping("{id}")
    public String showProduct(@PathVariable long id, Model model) {
        model.addAttribute("productDto", productService.getProductById(id));
        return "/product/product";
    }
}
