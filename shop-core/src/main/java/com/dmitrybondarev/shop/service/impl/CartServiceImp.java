package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.CartRepository;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.CartService;
import com.dmitrybondarev.shop.util.exception.ProductNotFoundException;
import com.dmitrybondarev.shop.util.exception.UserNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    private UserRepository userRepository;

    private ProductRepository productRepository;

    private CartRepository cartRepository;

    public CartServiceImp(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Loggable
    @Transactional
    public Cart getCartByUserEmail(String emailUser, String cartUID) throws UserNotFoundException {
        User user = userRepository.findByEmail(emailUser)
                .orElseThrow(() -> new UserNotFoundException("No user found with email: "+ emailUser));

        Cart cart = this.checkCartFromUserAndInit(user);

        this.checkHavingUnauthorizedCart(cart, cartUID);

        return cart;
    }

    @Override
    @Loggable
    @Transactional
    public Cart getCartByByCardUID(String cartUID) {   // Not always use??

        Optional<Cart> optionalCart = cartRepository.findBySessionId(cartUID);

        if (!optionalCart.isPresent()) {
            Cart newCart = new Cart();
            newCart.setUser(null);
            newCart.setSessionId(cartUID);
            newCart.setProducts(new HashMap<>());
            return cartRepository.save(newCart);
        }
        return optionalCart.get();
    }

    @Override
    @Loggable
    @Transactional
    public void modificationCartByUserEmail(String emailUser, long productId, int amount, String cartUID) throws UserNotFoundException, ProductNotFoundException {
        User user = userRepository.findByEmail(emailUser)
                .orElseThrow(() -> new UserNotFoundException("No user found with email: "+ emailUser));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("No product found with id: "+ productId));

        Cart cart = this.checkCartFromUserAndInit(user);

        this.checkHavingUnauthorizedCart(cart, cartUID);

        this.modificationProductAmount(cart, product, amount);
        cart.setSessionId(null);
        cartRepository.save(cart);
        userRepository.save(user);
    }

    @Override
    public void modificationCartByCartId(String cartUid, long productId, int amount) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("No product found with id: "+ productId));

        Cart cart = cartRepository.findBySessionId(cartUid).orElseGet(Cart::new);

        this.modificationProductAmount(cart, product, amount);
        cart.setUser(null);
        cart.setSessionId(cartUid);
        cartRepository.save(cart);
    }

//  ============  NON-API  ==================

    private void modificationProductAmount(Cart cart, Product product, int amount) {
        Map<Product, Integer> products = cart.getProducts();
        if (products == null) {
            products = new HashMap<>();
            cart.setProducts(products);
        }

        if (products.containsKey(product)) {
            int newAmount = products.get(product) + amount;
            if (newAmount > 0) {
                products.put(product, newAmount);
            } else {
                products.remove(product);
            }

        } else {
            if (amount > 0) {
                products.put(product, amount);
            }
        }
    }

    private void checkHavingUnauthorizedCart(Cart authorizedCart, String cartUID) {
        Optional<Cart> optionalCart = cartRepository.findBySessionId(cartUID);

        optionalCart.ifPresent(cartByCartUID -> this.mergeCarts(authorizedCart, cartByCartUID));
    }

    private void mergeCarts(Cart authorizedCart, Cart sessionCart) {
        Map<Product, Integer> authorizedProducts = authorizedCart.getProducts();
        Map<Product, Integer> sessionProducts = sessionCart.getProducts();
        for (Map.Entry<Product, Integer> entry : sessionProducts.entrySet()) {
            Product entryProduct = entry.getKey();
            Integer entryAmount = entry.getValue();

            if (authorizedProducts.containsKey(entryProduct)) {
                int newAmount = authorizedProducts.get(entryProduct) + entryAmount;
                authorizedProducts.put(entryProduct, newAmount);
            } else {
                authorizedProducts.put(entryProduct, entryAmount);
            }
        }
        cartRepository.delete(sessionCart);
        cartRepository.save(authorizedCart);
    }

    private Cart checkCartFromUserAndInit(User user) {
        Cart cart = user.getCart();
        if(cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProducts(new HashMap<>());
            cart = cartRepository.save(newCart);
            user.setCart(cart);
            userRepository.save(user);
        }
        return cart;
    }
}
