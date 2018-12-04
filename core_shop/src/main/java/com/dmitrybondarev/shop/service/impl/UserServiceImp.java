package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.VerificationToken;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import com.dmitrybondarev.shop.repository.api.VerificationTokenRepo;
import com.dmitrybondarev.shop.service.api.UserService;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private UserRepo userRepo;

    private VerificationTokenRepo verificationTokenRepo;

    public UserServiceImp(UserRepo userRepo, VerificationTokenRepo verificationTokenRepo) {
        this.userRepo = userRepo;
        this.verificationTokenRepo = verificationTokenRepo;
    }

    @Override
    @Loggable
    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException {
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = this.mapUserDtoToUser(userDto);
        user.setUsername(userDto.getEmail());   // TODO See after
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setId(null);

        userRepo.save(user);
        return user;
    }

    @Override
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepo.update(user);
    }

    @Override
    @Transactional
    public User getUserByVerificationToken(String verificationToken) {
        return verificationTokenRepo.findByToken(verificationToken).getUser();
    }

    @Override
    @Transactional
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepo.save(myToken);
    }

    @Override
    @Transactional
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = verificationTokenRepo.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        verificationTokenRepo.save(vToken);
        return vToken;
    }

    @Override
    @Transactional
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepo.findByToken(VerificationToken);
    }

    @Override
    @Loggable
    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) {
            UserDto userDto = this.mapUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoByUsername(String username) {
        User user = userRepo.findByEmail(username);
        return this.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoById(long id) {
        User user = userRepo.findById(id);
        return this.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto editUser(UserDto userDto) {
        User user = this.mapUserDtoToUser(userDto);
        userRepo.update(user);
        return userDto;
    }




// ============== NON-API ============

    private boolean emailExist(String email) {
        User user = userRepo.findByEmail(email);
        return user != null;
    }

    private UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEnabled(user.isEnabled());
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
        user.setEnabled(userDto.isEnabled());
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
