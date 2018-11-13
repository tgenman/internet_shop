package com.dmitrybondarev.controller.admin;

import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.service.api.ProductService;
import lombok.extern.log4j.Log4j;
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
import java.util.List;
import java.util.Map;

@Log4j
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView showListOfProducts() {
        log.info("admin showListOfProducts start");
        Map<String, List<ProductDto>> allProducts = productService.getAllProducts();
        for (String s : allProducts.keySet()) {
            log.info("Category = " + s);
        }
        log.info("admin showListOfProducts end");
        return new ModelAndView("admin/product/productList.jsp", "productDtos", productService.getAllProducts());
    }

    @GetMapping("/new")
    public ModelAndView showProductCreationForm() {
        log.info("admin showProductCreationForm start");
        ModelAndView mAV = new ModelAndView("admin/product/newProduct.jsp", "productDto", new ProductDto());
        log.info("admin showProductCreationForm end");
        return mAV;
    }

    @PostMapping("/new")
    public ModelAndView addNewProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                      BindingResult result, Errors errors) {
        log.info("addNewProduct start");
        if (result.hasErrors()) {
            log.info("addNewProduct. Error in form");
            return new ModelAndView("/admin/product/newProduct.jsp", "productDto", productDto);
        } else {
            productService.addNewProductToStock(productDto);
            log.info("addNewProduct. Everything is ok. redirect");
            return new ModelAndView("redirect:/admin/product");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView showProductEditForm(@PathVariable long id) {
        log.info("showProductEditForm start");
        ProductDto productDto = productService.getProductById(id);
        productDto.setId(id);
        log.info("showProductEditForm end");
        return new ModelAndView("/admin/product/editProduct.jsp", "productDto", productDto);
    }

    @PostMapping("/{id}")
    public ModelAndView editProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                    BindingResult result, Errors errors) {
        log.info("editProduct start");

        if (result.hasErrors()) {
            log.info("editProduct. Error in form");
            return new ModelAndView("redirect:/product/{" + productDto.getId() + "}/", "productDto", productDto);
        } else {
            productService.editProductToStock(productDto);
            log.info("editProduct. Everything is ok. redirect");
            return new ModelAndView("redirect:/admin/product");
        }
    }

    @DeleteMapping("/{id}")
    public String removeProduct(@PathVariable long id) {
        log.info("removeProduct start");
        productService.removeProductFromStock(id);
        log.info("removeProduct end");
        return "redirect:/admin/product/";
    }
}
