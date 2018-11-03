package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {

    Collection<User> getAllUsers();

    boolean registerNewUser(UserDto userDto);
}
