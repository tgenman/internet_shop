package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.service.api.CartService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Log4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ModelAndView showCart() {
        log.info("showCart start");
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Map<Product, Integer> cart = cartService.getCartByUserId(idUser);

        log.info("showCart end");
        return new ModelAndView("cart/showCart.jsp", "cart", cart);
    }

    @GetMapping("/{idProduct}")
    public ModelAndView addProductToCart(@PathVariable long idProduct) {
        log.info("addProductToCart start");
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.addProduct(idUser, idProduct);
        log.info("addProductToCart end");
        return new ModelAndView("redirect:/product");
    }

    @PostMapping("/delete/{idProduct}")
    public ModelAndView deleteProductFromCart(@PathVariable long idProduct) {
        log.info("deleteProductFromCart start");
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.deleteProduct(idUser, idProduct);
        log.info("deleteProductFromCart end");
        return new ModelAndView("redirect:/cart");
    }


}
