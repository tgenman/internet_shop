package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.repositories.api.UserRepo;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Log4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
//    @Transactional
    public boolean registerNewUser(UserDto userDto) {
        boolean result = false;

        log.info("Register new User. UserDto(email=" + userDto.getEmail());

//        User byEmail = userRepo.findByUsername(userDto.getEmail());
        User byEmail = null;
        if (byEmail == null) {
            User user = new User();
            mapper.map(userDto, user);
            user.setUsername(userDto.getEmail());

            log.trace("Converted UserDto to User");
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));

            userRepo.save(user);
            result = true;
            log.info("Register new User. End");
        } else {
            log.warn("User with username/email = " + userDto.getEmail() + " already exists");
        }
        return result;
    }

    @Override
//    @Transactional
    public List<User> getAllUsers() {
        log.info("Get All Users. Start");
        List<User> all = userRepo.findAll();
        log.info("Get all Users. end");
        return all;
    }


    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
