package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Log4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ModelAndView showProductList() {
        log.info("showProductList start");
        Map<String, List<ProductDto>> productsDtos = productService.getProductsFromStock();
        log.info("showProductList end");

        return new ModelAndView("product/productList.jsp", "productDtos", productsDtos);
    }

    @GetMapping("{id}")
    public ModelAndView showProduct(@PathVariable long id) {
        log.info("showProduct start");
        ProductDto productDtoById = productService.getProductById(id);
        log.info("showProduct end");

        return new ModelAndView("/product/product.jsp", "productDto", productDtoById);
    }

}
