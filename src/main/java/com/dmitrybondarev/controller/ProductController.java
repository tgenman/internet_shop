package com.dmitrybondarev.controller;

import com.dmitrybondarev.exception.TitleExistException;
import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Log4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public ModelAndView showProductsForUser() {
        log.info("ProductsForUser GET request");
        return new ModelAndView("product/productsForUser", "productDtos", productService.getProductsFromStock());
    }

    @GetMapping("/new")
    public ModelAndView showProductCreationForm() {
        log.info("ProductCreation GET request");
        return new ModelAndView("product/newProduct", "productDto", new ProductDto());
    }

    @PostMapping("/new")
    public ModelAndView AddNewProductToStock(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                             BindingResult result, Errors errors) {
        log.info("into post AddNewProductToStock");

        Product added = new Product();
        if (!result.hasErrors()) {
            log.info("1");
            added = this.addProduct(productDto, result);
        }
        if (added == null) {
            log.info("2");
            result.rejectValue("title", "message.regError");
        }
        if (result.hasErrors()) {
            log.info("3");
            return new ModelAndView("product/new", "productDto", productDto);
        } else {
            log.info("4");
            return new ModelAndView("redirect:/store/inventory");
        }
    }


// ============== NON-API ============


    private Product addProduct(ProductDto productDto, BindingResult result) { //TODO Resolve by ExceptionHandler
        Product added = null;
        try {
            added = productService.addNewProductToStock(productDto);
        } catch (TitleExistException e) {
            return null;
        }
        return added;
    }
}
