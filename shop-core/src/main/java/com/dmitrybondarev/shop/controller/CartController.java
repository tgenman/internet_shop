package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.service.api.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Loggable
    @GetMapping
    public String showCart(Model model) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Map<Product, Integer> cart = cartService.getCartByUserId(idUser);
        model.addAttribute("cart", cart);
        return "cart/showCart";
    }

    @Loggable
    @GetMapping("/{idProduct}")
    public String addProductToCart(@PathVariable long idProduct) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.addProduct(idUser, idProduct);
        return "redirect:/product";
    }

    @Loggable
    @PostMapping("/delete/{idProduct}")
    public String deleteProductFromCart(@PathVariable long idProduct) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        cartService.deleteProduct(idUser, idProduct);
        return "redirect:/cart";
    }


}
