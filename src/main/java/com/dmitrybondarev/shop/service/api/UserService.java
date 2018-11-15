package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {

    Collection<UserDto> getAllUsers();

    UserDto getUserDtoByUsername(String username);

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    UserDto getUserDtoById(long id);

    UserDto editUser(UserDto userDto);

    User mapUserDtoToUser(UserDto userDto);
}
