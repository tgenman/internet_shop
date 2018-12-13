package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.CategoryService;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.exception.CategoryNotFoundException;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private ProductService productService;

    private CategoryService categoryService;

    private static final String PRODUCT_DTO = "productDto";

    public AdminProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Loggable
    @GetMapping
    public String showListOfProducts(@RequestParam(required = false, defaultValue = "") String filter,
                                     Model model) {

        Map<CategoryDto, List<ProductDto>> productDtos;
        try {
            productDtos = productService.getAllExistProductDtosByFilter(filter);
        } catch (CategoryNotFoundException e) {
            productDtos = new HashMap<>();
        }

        model.addAttribute("allProductDto", productDtos);
        return "admin/product/productList";
    }

    @Loggable
    @GetMapping("/new")
    public String showProductCreationForm(Model model) {
        List<CategoryDto> allCategoryDto = categoryService.getAllCategoryDto();

        model.addAttribute(PRODUCT_DTO, new ProductDto());
        model.addAttribute("allCategoryDto", allCategoryDto);
        return "admin/product/newProduct";
    }

    @Loggable
    @PostMapping("/new")
    public String addNewProduct(
            @Valid ProductDto productDto,
            BindingResult result,
            @RequestParam(value = "file") MultipartFile file,
            Model model) throws IOException {

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
    public String showProductEditForm(@PathVariable long id,
                                      Model model,
                                      HttpSession httpSession) {

        httpSession.removeAttribute("idProductForEdit");
        httpSession.setAttribute("idProductForEdit", id);
        ProductDto productDto = productService.getProductById(id);
        model.addAttribute(PRODUCT_DTO, productDto);
        model.addAttribute("allCategoriesDto", categoryService.getAllCategoryDto());
        return "/admin/product/productEdit.html";
    }

    @Loggable
    @PostMapping
    public String editProduct(
            @Valid ProductDto productDto,
            BindingResult result,
            HttpServletRequest request,
            Model model) {

        long idProductForEdit = (Long) request.getSession().getAttribute("idProductForEdit");
        productDto.setId(idProductForEdit);

        if (result.hasErrors()) {
            model.addAttribute(PRODUCT_DTO, productDto);
            return "/admin/product/"+ productDto.getId();
        }

        productService.editProductInStock(productDto);
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
