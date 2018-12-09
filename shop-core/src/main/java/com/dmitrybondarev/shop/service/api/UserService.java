package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    void enableUser(User user);

    void createPasswordResetTokenForUser(UserDto userDto, String token);

    void createVerificationToken(User user, String token);

    User getUserByVerificationToken(String verificationToken);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String existingVerificationToken);

    void changeUserPassword(Long id, String password);




    List<UserDto> getAllUsers();

    UserDto getUserDtoById(long id);

    UserDto getUserDtoByEmail(String email);

    UserDto editUser(UserDto userDto);


}
