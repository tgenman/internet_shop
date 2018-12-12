package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.service.api.CategoryService;
import com.dmitrybondarev.shop.util.exception.CategoryExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes("idCategoryForEdit")
public class AdminCategoryController {

    private CategoryService categoryService;

    private static final String CATEGORY_DTO = "categoryDto";

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Loggable
    @GetMapping
    public String showListOfCategories(Model model) {

        model.addAttribute("allCategoryDto", categoryService.getAllCategoryDto());
        return "admin/category/categoryList";
    }

    @Loggable
    @GetMapping("/new")
    public String showCategoryCreationForm(Model model) {
        model.addAttribute(CATEGORY_DTO, new CategoryDto());
        return "admin/product/newCategory";
    }

    @Loggable
    @PostMapping("/new")
    public String addNewCategory(
            @Valid CategoryDto categoryDto,
            BindingResult result,
            Model model,
            Errors errors) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/category/newCategory";
        }

        try {
            categoryService.addNewCategory(categoryDto);
        } catch (CategoryExistsException e) {
            result.rejectValue("category", "message.regError");
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/product/newProduct";
        }

        return "redirect:/admin/category";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showCategoryEditForm(@PathVariable long id,
                                       Model model,
                                       HttpSession httpSession) {
        httpSession.setAttribute("idCategoryForEdit", id);
        CategoryDto categoryDto = categoryService.getCategoryDtoById(id);
        model.addAttribute(CATEGORY_DTO, categoryDto);
        return "/admin/category/categoryEdit.html";
    }

    @Loggable
    @PostMapping
    public String editCategory(@Valid CategoryDto categoryDto,
                              BindingResult result,
                              HttpServletRequest request,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/category/"+ categoryDto.getId();
        }

        long idCategoryForEdit = Long.parseLong((String) request.getSession().getAttribute("idCategoryForEdit"));
        categoryDto.setId(idCategoryForEdit);

        categoryService.editCategory(categoryDto);
        return"redirect:/admin/product";
    }
}
