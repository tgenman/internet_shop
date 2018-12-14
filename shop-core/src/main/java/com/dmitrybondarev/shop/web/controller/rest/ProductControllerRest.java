package com.dmitrybondarev.shop.web.controller.rest;

import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.service.api.StatisticService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductControllerRest {

    private ProductService productService;

    public ProductControllerRest(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping("/list")
    public List<ProductDtoRest> getTopFiveProductsDtoRest() {
        return productService.getListAllAdvertisingProductDtoRest();
    }
}
