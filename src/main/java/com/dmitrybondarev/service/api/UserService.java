package com.dmitrybondarev.service.api;

import com.dmitrybondarev.exception.EmailExistsException;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {

    Collection<UserDto> getAllUsers();

    UserDto getUserDtoByUsername(String username);

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    UserDto getUserDtoById(long id);

    UserDto editUser(UserDto userDto);
}
