package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes("idProductForEdit")
public class AdminProductController {

    private ProductService productService;

    private static final String PRODUCT_DTO = "productDto";

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @Loggable
    @GetMapping
    public String showListOfProducts(@RequestParam(required = false, defaultValue = "") String filter,
                                     Model model) {

        model.addAttribute("allProductDto", productService.getAllExistProductsByFilter(filter));
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
    public String addNewProduct(
            @Valid ProductDto productDto,
            BindingResult result,
            @RequestParam(value = "file") MultipartFile file,
            Model model,
            Errors errors) throws IOException {

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
    public String showProductEditForm(@PathVariable long id, Model model, HttpSession httpSession) {
        httpSession.setAttribute("idProductForEdit", id);
        ProductDto productDto = productService.getProductById(id);
        model.addAttribute(PRODUCT_DTO, productDto);
        return "/admin/product/productEdit.html";
    }

    @Loggable
    @PostMapping
    public String editProduct(
            @Valid ProductDto productDto,
            BindingResult result,
            @ModelAttribute long idProductForEdit,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(PRODUCT_DTO, productDto);
            return String.format("/admin/product/", productDto.getId());
        }

        productService.editProductToStock(productDto);
        return"redirect:/admin/product";
    }

    @Loggable
    @PostMapping("/{id}/inactivate")
    public String inactivateProduct(@PathVariable long id) {
        productService.inactivateProduct(id);
        return "redirect:/admin/product/";
    }

    @Loggable
    @PostMapping("/{id}/activate")
    public String activateProduct(@PathVariable long id) {
        productService.activateProduct(id);
        return "redirect:/admin/product/";
    }
}
