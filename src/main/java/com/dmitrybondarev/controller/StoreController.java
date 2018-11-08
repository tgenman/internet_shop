package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Log4j
@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private ProductService productService;

    @GetMapping("/inventory")
    public ModelAndView showInventory() {
        log.info("Inventory GET request");
        Map<String, List<ProductDto>> allProducts = productService.getAllProducts();
        for (String s : allProducts.keySet()) {
            log.info("Category = " + s);
        }
        log.info("END");
        return new ModelAndView("store/inventory", "productDtos", productService.getAllProducts());
    }
}
