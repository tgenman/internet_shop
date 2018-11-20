package com.dmitrybondarev.shop.controller.admin;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Loggable
    public ModelAndView showListOfProducts() {
        return new ModelAndView("admin/product/productList.jsp", "productDtos", productService.getAllProducts());
    }

    @GetMapping("/new")
    @Loggable
    public ModelAndView showProductCreationForm() {
        return new ModelAndView("admin/product/newProduct.jsp", "productDto", new ProductDto());
    }

    @PostMapping("/new")
    @Loggable
    public ModelAndView addNewProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                      BindingResult result, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("/admin/product/newProduct.jsp", "productDto", productDto);
        } else {
            productService.addNewProductToStock(productDto);
            return new ModelAndView("redirect:/admin/product");
        }
    }

    @GetMapping("/{id}")
    @Loggable
    public ModelAndView showProductEditForm(@PathVariable long id) {
        ProductDto productDto = productService.getProductById(id);
        productDto.setId(id);
        return new ModelAndView("/admin/product/editProduct.jsp", "productDto", productDto);
    }

    @PostMapping("/{id}")
    @Loggable
    public ModelAndView editProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                    BindingResult result, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/product/{" + productDto.getId() + "}/", "productDto", productDto);
        } else {
            productService.editProductToStock(productDto);
            return new ModelAndView("redirect:/admin/product");
        }
    }

    @DeleteMapping("/{id}")
    @Loggable
    public String removeProduct(@PathVariable long id) {
        productService.removeProductFromStock(id);
        return "redirect:/admin/product/";
    }
}
