package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.exception.EmailExistsException;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.repository.api.UserRepo;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        log.info("Get All Users. Start");

        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) {
            UserDto userDto = new UserDto();
            mapper.map(user, userDto);
            userDtos.add(userDto);
        }

        log.info("Get users Users. end");
        return userDtos;
    }

    @Override
    @Transactional
    public UserDto getUserDtoByUsername(String username) {
        User byUsername = userRepo.findByUsername(username);
        UserDto userDto = new UserDto();
        mapper.map(byUsername, userDto);
        return userDto;
    }


//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepo.findByUsername(username);
//    }

    @Override
    @Transactional
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + accountDto.getEmail());
        }

        User user = new User();
        mapper.map(accountDto, user);
        user.setUsername(accountDto.getEmail());   // TODO See after
        user.setRoles(Arrays.asList("ROLE_USER"));

        return userRepo.save(user);

    }

// ============== NON-API ============

    private boolean emailExist(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

//    @Override
//    @Transactional
//    public boolean registerNewUser(UserDto userDto) {
//        boolean result = false;
//
//        log.info("Register new User. UserDto(email=" + userDto.getEmail());
//
//        User byEmail = userRepo.findByUsername(userDto.getEmail());
//        if (byEmail == null) {
//            User user = new User();
//            mapper.map(userDto, user);
//            user.setUsername(userDto.getEmail());
//
//            log.trace("Converted UserDto to User");
//            user.setActive(true);
//            user.setRoles(Collections.singleton(Role.USER));
//
//            userRepo.save(user);
//            result = true;
//            log.info("Register new User. End");
//        } else {
//            log.warn("User with username/email = " + userDto.getEmail() + " already exists");
//        }
//        return result;
//    }
}
