package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Cart;
import com.dmitrybondarev.model.Order;
import com.dmitrybondarev.model.dto.CartDto;
import com.dmitrybondarev.model.dto.OrderDto;
import com.dmitrybondarev.repository.api.CartRepo;
import com.dmitrybondarev.service.api.CartService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private DozerBeanMapper mapper;



// ============== NON-API ============

    private CartDto mapCartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        mapper.map(cart, cartDto);
        return cartDto;
    }

    private Cart mapCartDtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        mapper.map(cartDto, cart);
        return cart;
    }
}
