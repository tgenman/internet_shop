package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.repository.api.ProductRepo;
import com.dmitrybondarev.repository.api.UserRepo;
import com.dmitrybondarev.service.api.CartService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Log4j
@Service
public class CartServiceImp implements CartService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public Map<Product, Integer> getCartByUserId(long idUser) {
        log.info("getCartDtoByUserId start.");
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            cart = new HashMap<>();
            user.setCart(cart);
            userRepo.updateUser(user);
        }
        log.info("getCartDtoByUserId end.");
        return cart;
    }

    @Override
    @Transactional
    public void addProduct(long idUser, long idProduct) {
        log.info("addProduct start.");
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if(cart == null) {
            log.info("cart == null, create new Cart");
            cart = new HashMap<>();
        }
        cart.put(product, 1);
        userRepo.updateUser(user);
        log.info("addProduct end.");
    }

    @Override
    @Transactional
    public void deleteProduct(long idUser, long idProduct) {
        log.info("deleteProduct start.");
        Product product = productRepo.findById(idProduct);
        User user = userRepo.findById(idUser);
        Map<Product, Integer> cart = user.getCart();
        if (cart == null) {
            log.info("cart == null, end");
            return;
        }
        cart.remove(product);
        userRepo.updateUser(user);
        log.info("deleteProduct end.");
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
