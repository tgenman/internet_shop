package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.exception.EmailExistsException;
import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.AddressDto;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.repository.api.UserRepo;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException {

        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = this.mapUserDtoToUser(userDto);
        user.setUsername(userDto.getEmail());   // TODO See after
        user.setRoles(Collections.singleton(Role.USER));

        return userRepo.saveUser(user);

    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        log.info("Get All Users. Start");

        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) {
            UserDto userDto = this.mapUserToUserDto(user);
            userDtos.add(userDto);
        }

        log.info("Get users Users. end");
        return userDtos;
    }

    @Override
    @Transactional
    public UserDto getUserDtoByUsername(String username) {
        log.info("getUserDtoByUsername start");
        User user = userRepo.findByUsername(username);
        UserDto userDto = this.mapUserToUserDto(user);
        log.info("getUserDtoByUsername end");
        return userDto;
    }

    @Override
    @Transactional
    public UserDto getUserDtoById(long id) {
        log.info("getUserDtoById start");
        User user = userRepo.findById(id);
        log.info(user.toString());
        UserDto userDto = this.mapUserToUserDto(user);
        log.info("getUserDtoByUsername start");
        return userDto;
    }

    @Override
    @Transactional
    public UserDto editUser(UserDto userDto) {
        log.info("editUser start");
        log.info("input: " + userDto.toString());

        User user = this.mapUserDtoToUser(userDto);

        log.info("dto: " + user.toString());

        userRepo.updateUser(user);
        log.info("editUser end");
        return userDto;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername = " + username);
        User byUsername = userRepo.findByUsername(username);
        log.info("found user = " + byUsername.getUsername());
        return byUsername;
    }


// ============== NON-API ============

    private boolean emailExist(String email) {
        User user = userRepo.findByEmail(email);
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
