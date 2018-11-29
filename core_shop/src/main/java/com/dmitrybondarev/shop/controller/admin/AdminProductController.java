package com.dmitrybondarev.shop.controller.admin;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private ProductService productService;

    private static final String PRODUCT_DTO = "productDto";

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping
    public String showListOfProducts(Model model) {
        model.addAttribute("productDtos", productService.getAllProducts());
        return "admin/product/productList";
    }

    @Loggable
    @GetMapping("/new")
    public String showProductCreationForm(Model model) {
        model.addAttribute(PRODUCT_DTO, new ProductDto());
        return "admin/product/newProduct";
    }

    @Loggable
    @PostMapping("/new")
    public String addNewProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(PRODUCT_DTO, productDto);
            return "/admin/product/newProduct";
        } else {
            productService.addNewProductToStock(productDto);
            return "redirect:/admin/product";
        }
    }

    @Loggable
    @GetMapping("/{id}")
    public String showProductEditForm(@PathVariable long id, Model model) {
        ProductDto productDto = productService.getProductById(id);
        productDto.setId(id);
        model.addAttribute(PRODUCT_DTO, productDto);
        return "/admin/product/editProduct";
    }

    @Loggable
    @PostMapping("/{id}")
    public String editProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(PRODUCT_DTO, productDto);
            return String.format("/admin/product/%s", productDto.getId());   //TODO Something strange
        } else {
            productService.editProductToStock(productDto);
            return"redirect:/admin/product";
        }
    }

//    @Loggable     TODO Implement inactivation
//    @DeleteMapping("/{id}")
//    public String removeProduct(@PathVariable long id) {
//        productService.removeProductFromStock(id);
//        return "redirect:/admin/product/";
//    }
}
