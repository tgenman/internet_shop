package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.repo.api.UserRepo;
import com.dmitrybondarev.service.api.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DozerBeanMapper mapper;

    public List<User> getAllClients() {
        return userRepo.getAllClients();
    }

    public boolean registerNewClient(UserDto userDto) {

        User byEmail = userRepo.findByUsername(userDto.getEmail());
        if (byEmail != null) return false;

        User user = new User();
        mapper.map(userDto, user);
        user.setUsername(userDto.getEmail());

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.addClient(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
