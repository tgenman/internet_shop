package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {

    List<UserDto> getAllUsers();

    UserDto getUserDtoByUsername(String username);

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    UserDto getUserDtoById(long id);

    UserDto editUser(UserDto userDto);

    User mapUserDtoToUser(UserDto userDto);
}
