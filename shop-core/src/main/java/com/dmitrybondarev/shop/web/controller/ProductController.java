package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping
    public String showProductList(@RequestParam(required = false, defaultValue = "") String filter,
                                  Model model) {
        model.addAttribute("allProductDto", productService.getProductsFromStockByFilter(filter));
        return "product/productList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showProduct(@PathVariable long id, Model model) {
        model.addAttribute("productDto", productService.getProductById(id));
        return "/product/product";
    }
}
