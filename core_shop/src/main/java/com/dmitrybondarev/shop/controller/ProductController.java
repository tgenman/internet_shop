package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    @Loggable
    public ModelAndView showProductList() {
        Map<String, List<ProductDto>> productsDtos = productService.getProductsFromStock();

        return new ModelAndView("product/productList.jsp", "productDtos", productsDtos);
    }

    @GetMapping("{id}")
    @Loggable
    public ModelAndView showProduct(@PathVariable long id) {
        ProductDto productDtoById = productService.getProductById(id);

        return new ModelAndView("/product/product.jsp", "productDto", productDtoById);
    }

}
