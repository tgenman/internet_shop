package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ModelAndView("product/showProductsForUser", "productDtos", productService.getProductsFromStock());
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
        if (result.hasErrors()) {
            log.info("Error in form");
            return new ModelAndView("product/newProduct", "productDto", productDto);
        } else {
            productService.addNewProductToStock(productDto);
            log.info("Everything is ok. redirect");
            return new ModelAndView("redirect:/store/inventory");
        }
    }

    @GetMapping("{id}/info")
    public ModelAndView showProductInfo(@PathVariable long id) {
        log.info("showProductInfo GET");
        return new ModelAndView("/product/showProductInfo", "productDto", productService.getProductById(id));
    }

    @GetMapping("/{id}")
    public ModelAndView showProductEditForm(@PathVariable long id) {
        log.info("showProductEditForm GET");
        ProductDto productDto = productService.getProductById(id);
        productDto.setId(id);
        return new ModelAndView("/product/editProduct", "productDto", productService.getProductById(id));
    }

    @PostMapping("/{id}")
    public ModelAndView editProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                    BindingResult result, Errors errors) {
        log.info("editProduct");

        if (result.hasErrors()) {
            log.info("Error in form");
            return new ModelAndView("redirect:/product/{" + productDto.getId() + "}/", "productDto", productDto);
        } else {
            productService.editProductToStock(productDto);
            log.info("Everything is ok. redirect");
            return new ModelAndView("redirect:/store/inventory");
        }
    }

    @DeleteMapping("/{id}")
    public String removeProduct(@PathVariable long id) {
        log.info("removeProduct");
        productService.removeProductFromStock(id);
        return "redirect:/story/inventory";
    }
}
