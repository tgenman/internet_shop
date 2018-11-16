package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import com.dmitrybondarev.shop.service.api.CartService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Loggable
    @Transactional
    public Map<Product, Integer> getCartByUserId(long idUser) {
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            cart = new HashMap<>();
            user.setCart(cart);
            userRepo.update(user);
        }
        return cart;
    }

    @Override
    @Loggable
    @Transactional
    public void addProduct(long idUser, long idProduct) {
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if(cart == null) {
            cart = new HashMap<>();
        }
        cart.put(product, 1);
        userRepo.update(user);
    }

    @Override
    @Loggable
    @Transactional
    public void deleteProduct(long idUser, long idProduct) {
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            return;
        }
        cart.remove(product);
        userRepo.update(user);
    }


// ============== NON-API ============

    private UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        mapper.map(user, userDto);
        return userDto;
    }

    private User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        mapper.map(userDto, user);
        return user;
    }
}
