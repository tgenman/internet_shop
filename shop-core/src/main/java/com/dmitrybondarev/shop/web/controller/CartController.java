package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.CartDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Loggable
    @GetMapping
    public String showCart(@AuthenticationPrincipal UserDetails userDetails,
                           Model model) {
        Cart cart;
        if (userDetails == null) {
            cart = cartService.getCartByBySessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
        } else {
            cart = cartService.getCartByUserEmail(userDetails.getUsername());
        }

        model.addAttribute("sum", this.countSum(cart));
        model.addAttribute("cart", cart);
        return "cart/showCart";
    }

    @Loggable
    @GetMapping("/{productId}")
    public String addProductToCart(@PathVariable long productId,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            cartService.modificationCartBySessionId(
                    RequestContextHolder.currentRequestAttributes().getSessionId(),
                    productId, 1);
            return "redirect:/product";
        }

        cartService.modificationCartByUserEmail(
                userDetails.getUsername(),
                productId,1);
        return "redirect:/product";
    }

    @Loggable
    @DeleteMapping("/delete/{productId}")
    public String deleteProductFromCart(@PathVariable long productId,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            cartService.modificationCartBySessionId(
                    RequestContextHolder.currentRequestAttributes().getSessionId(),
                    productId, -1);
            return "redirect:/cart";
        }

        cartService.modificationCartByUserEmail(
                userDetails.getUsername(),
                productId, -1);
        return "redirect:/cart";
    }


//  =============== NON_API =================

    private int countSum(Cart cart) {
        int sum = 0;
        Map<Product, Integer> products = cart.getProducts();
        for (Map.Entry<Product, Integer> entries : products.entrySet()) {
            int price = entries.getKey().getPrice();
            int amount = entries.getValue();
            sum += price * amount;
        }
        return sum;
    }
}
