package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Loggable
    @GetMapping
    public String showCart(
            @CookieValue(value = "cartUID", defaultValue = "not") String cartUID,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response,
            Model model) {

        Cart cart;
        if (userDetails == null) {
            if (cartUID.equals("not")) cartUID = UUID.randomUUID().toString();
            cart = cartService.getCartByByCardUID(cartUID);
        } else {
            cart = cartService.getCartByUserEmail(userDetails.getUsername(), cartUID);
        }

        response.addCookie(new Cookie("cartUID", cartUID));

        model.addAttribute("sum", this.countSum(cart));
        model.addAttribute("cart", cart);
        return "cart/showCart";
    }

    @Loggable
    @GetMapping("/{productId}")
    public String addProductToCart(@PathVariable long productId,
                                   @CookieValue(value = "cartUID", defaultValue = "not") String cartUID,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   HttpServletResponse response) {

        if (userDetails == null) {
            if (cartUID.equals("not")) cartUID = UUID.randomUUID().toString();
            cartService.modificationCartByCartId(cartUID, productId, 1);
            return "redirect:/product";
        }

        cartService.modificationCartByUserEmail(userDetails.getUsername(), productId,1,cartUID);

        response.addCookie(new Cookie("cartUID", cartUID));
        return "redirect:/product";
    }

    @Loggable
//    @PostMapping("/delete/{productId}")
    @GetMapping("/delete/{productId}")
    public String deleteProductFromCart(@PathVariable long productId,
                                        @AuthenticationPrincipal UserDetails userDetails,
                                        @CookieValue(value = "cartUID", defaultValue = "not") String cartUID,
                                        HttpServletResponse response) {
        if (userDetails == null) {
            if (cartUID.equals("not")) cartUID = UUID.randomUUID().toString();
            cartService.modificationCartByCartId(cartUID, productId, -1);
            return "redirect:/cart";
        }

        response.addCookie(new Cookie("cartUID", cartUID));


        cartService.modificationCartByUserEmail(userDetails.getUsername(), productId, -1, cartUID);
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
