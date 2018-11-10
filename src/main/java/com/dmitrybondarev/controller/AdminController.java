package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.ProductDto;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.ProductService;
import com.dmitrybondarev.service.api.UserService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showAdminPanel() {
        log.info("showAdminPanel request");
        return "/admin/adminPanel.jsp";
    }


// ============== USER-CONTROLLERS ============


    @GetMapping("/user")
    public ModelAndView showListOfAllUsers() {
        log.info("admin showListOfAllUsers start");
        ModelAndView mAV = new ModelAndView("admin/user/userList.jsp", "userDtos", userService.getAllUsers());
        log.info("admin showListOfAllUsers end");
        return mAV;
    }

    @GetMapping("/user/{id}")
    public ModelAndView showUserEditForm(@PathVariable long id) {
        log.info("admin showMyselfUserEditForm start");
        UserDto userDtoByUsername = userService.getUserDtoById(id);

        ModelAndView mAV = new ModelAndView("admin/user/userEdit.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("roles", Role.values());

        log.info("admin showMyselfUserEditForm end");
        return mAV;
    }

    @PostMapping("/user/{id}")
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {  //TODO Implement editing User in admin
        return new ModelAndView("/home.jsp");
    }

    @DeleteMapping("/user/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {  //TODO Implement deleting User
        return new ModelAndView("/home.jsp");
    }



// ============== PRODUCT-CONTROLLERS ============


    @GetMapping("/product")
    public ModelAndView showListOfProducts() {
        log.info("admin showListOfProducts start");
        Map<String, List<ProductDto>> allProducts = productService.getAllProducts();
        for (String s : allProducts.keySet()) {
            log.info("Category = " + s);
        }
        log.info("admin showListOfProducts end");
        return new ModelAndView("admin/product/productList.jsp", "productDtos", productService.getAllProducts());
    }

    @GetMapping("/product/new")
    public ModelAndView showProductCreationForm() {
        log.info("admin showProductCreationForm start");
        ModelAndView mAV = new ModelAndView("admin/product/newProduct.jsp", "productDto", new ProductDto());
        log.info("admin showProductCreationForm end");
        return mAV;
    }

    @PostMapping("/product/new")
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

    @GetMapping("/product/{id}")
    public ModelAndView showProductEditForm(@PathVariable long id) {
        log.info("showProductEditForm start");
        ProductDto productDto = productService.getProductById(id);
        productDto.setId(id);
        log.info("showProductEditForm end");
        return new ModelAndView("/admin/product/editProduct.jsp", "productDto", productDto);
    }

    @PostMapping("/product/{id}")
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

    @DeleteMapping("/product/{id}")
    public String removeProduct(@PathVariable long id) {
        log.info("removeProduct start");
        productService.removeProductFromStock(id);
        log.info("removeProduct end");
        return "redirect:/story/inventory";
    }



// ============== ORDER-CONTROLLERS ============


    @GetMapping("/order")
    public ModelAndView showListOfOrders() {  //TODO Implement List Orders
        log.info("showListOfOrders start");

        log.info("showListOfOrders end");
        return new ModelAndView("/home.jsp");
    }

    @GetMapping("/order/{id}")
    public ModelAndView showOrderEditForm(@PathVariable long id) { //TODO Implement showOrderEditForm
        log.info("showOrderEditForm start");

        log.info("showOrderEditForm end");
        return new ModelAndView("/home.jsp");
    }

    @PostMapping("/order/{id}")
    public ModelAndView editOrder(@ModelAttribute("productDto") @Valid ProductDto productDto,
                                    BindingResult result, Errors errors) { //TODO Implement editOrder
        log.info("editOrder start");

        log.info("editOrder end");
        return new ModelAndView("/home.jsp");
    }

    @DeleteMapping("/order/{id}")
    public String removeOrder(@PathVariable long id) {   //TODO Implement editOrder
        log.info("removeOrder start");

        log.info("removeOrder end");
        return "/home.jsp";
    }
}
