package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.service.api.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    @Loggable
    public ModelAndView showCart() {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Map<Product, Integer> cart = cartService.getCartByUserId(idUser);

        return new ModelAndView("cart/showCart.jsp", "cart", cart);
    }

    @GetMapping("/{idProduct}")
    @Loggable
    public ModelAndView addProductToCart(@PathVariable long idProduct) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.addProduct(idUser, idProduct);
        return new ModelAndView("redirect:/product");
    }

    @PostMapping("/delete/{idProduct}")
    @Loggable
    public ModelAndView deleteProductFromCart(@PathVariable long idProduct) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.deleteProduct(idUser, idProduct);
        return new ModelAndView("redirect:/cart");
    }


}
