package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.VerificationToken;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    void enableUser(User user);

    User getUserByVerificationToken(String verificationToken);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);




    UserDto getUserDtoById(long id);

    UserDto getUserDtoByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto editUser(UserDto userDto);

    User mapUserDtoToUser(UserDto userDto);

}
