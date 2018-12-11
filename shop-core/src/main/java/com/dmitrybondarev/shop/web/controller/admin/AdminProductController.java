package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private ProductService productService;

    private static final String PRODUCT_DTO = "productDto";

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping
    public String showListOfProducts(Model model) {
        model.addAttribute("allProductDto", productService.getAllExistProducts());
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
                                @RequestParam("file") MultipartFile file,
                                BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute(PRODUCT_DTO, productDto);
            return "/admin/product/newProduct";
        }

        try {
            productService.addNewProductToStock(productDto, file);
        } catch (ProductExistsException e) {
            result.rejectValue("product", "message.regError");
            model.addAttribute(PRODUCT_DTO, productDto);
            return "/admin/product/newProduct";
        }

        return "redirect:/admin/product";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showProductEditForm(@PathVariable long id, Model model) {
        ProductDto productDto = productService.getProductById(id);
        model.addAttribute(PRODUCT_DTO, productDto);
        return "/admin/product/productEdit.html";
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

    @Loggable
    @PostMapping("/{id}/delete/inactivate")
    public String inactivateProduct(@PathVariable long id) {
        productService.inactivateProduct(id);
        return "redirect:/admin/product/";
    }

    @Loggable
    @PostMapping("/{id}/delete/activate")
    public String activateProduct(@PathVariable long id) {
        productService.activateProduct(id);
        return "redirect:/admin/product/";
    }
}
