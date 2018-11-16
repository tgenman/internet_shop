package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    @Loggable
    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException {
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = this.mapUserDtoToUser(userDto);
        user.setUsername(userDto.getEmail());   // TODO See after
        user.setRoles(Collections.singleton(Role.USER));
//        user.setId(null);

        userRepo.save(user);
        return user;

    }

    @Override
    @Loggable
    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) {
            UserDto userDto = this.mapUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return this.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoById(long id) {
        User user = userRepo.findById(id);
        return this.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto editUser(UserDto userDto) {
        User user = this.mapUserDtoToUser(userDto);
        userRepo.update(user);
        return userDto;
    }


    @Override
    @Loggable
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }


// ============== NON-API ============

    private boolean emailExist(String email) {
        User user = userRepo.findByUsername(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setActive(user.isActive());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setAddresses(user.getAddresses());
        userDto.setRoles(user.getRoles());
        userDto.setOrders(user.getOrders());
        return userDto;
    }

    @Override
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setActive(userDto.isActive());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setAddresses(userDto.getAddresses());
        user.setRoles(userDto.getRoles());
        user.setOrders(userDto.getOrders());
        return user;
    }


}
