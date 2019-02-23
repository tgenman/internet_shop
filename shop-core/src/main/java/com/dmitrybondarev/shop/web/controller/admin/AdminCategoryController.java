package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.service.api.CategoryService;
import com.dmitrybondarev.shop.util.exception.CategoryExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private CategoryService categoryService;

    private static final String CATEGORY_DTO = "categoryDto";

    private static final String ID_CATEGORY_FOR_EDIT = "idCategoryForEdit";

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
        return "admin/category/newCategory";
    }

    @Loggable
    @PostMapping("/new")
    public String addNewCategory(
            @Valid CategoryDto categoryDto,
            BindingResult result,
            Model model){

        if (result.hasErrors()) {
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/category/newCategory";
        }

        try {
            categoryService.addNewCategory(categoryDto);
        } catch (CategoryExistsException e) {
            result.rejectValue("category", "message.regError");
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/category/newCategory";
        }

        return "redirect:/admin/category";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showCategoryEditForm(@PathVariable long id,
                                       Model model,
                                       HttpSession httpSession) {
        httpSession.removeAttribute(ID_CATEGORY_FOR_EDIT);
        httpSession.setAttribute(ID_CATEGORY_FOR_EDIT, id);

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

        long idCategoryForEdit = (Long) request.getSession().getAttribute(ID_CATEGORY_FOR_EDIT);
        categoryDto.setId(idCategoryForEdit);

        if (result.hasErrors()) {
            model.addAttribute(CATEGORY_DTO, categoryDto);
            return "/admin/category/"+ categoryDto.getId();
        }

        categoryService.editCategory(categoryDto);
        return"redirect:/admin/category";
    }
}
