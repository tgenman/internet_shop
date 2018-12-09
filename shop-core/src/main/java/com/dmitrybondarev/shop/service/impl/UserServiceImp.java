package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.PasswordResetToken;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.repository.token.PasswordResetTokenRepository;
import com.dmitrybondarev.shop.repository.token.VerificationTokenRepository;
import com.dmitrybondarev.shop.service.api.UserService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    private VerificationTokenRepository verificationTokenRepository;

    private PasswordResetTokenRepository passwordResetTokenRepository;

    private PasswordEncoder passwordEncoder;

    private MapperUtil mapperUtil;

    public UserServiceImp(UserRepository userRepository,
                          VerificationTokenRepository verificationTokenRepository,
                          PasswordResetTokenRepository passwordResetTokenRepository,
                          PasswordEncoder passwordEncoder,
                          MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException {
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = mapperUtil.mapUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setEnabled(false);
        user.setId(null);

        userRepository.save(user);
        return user;
    }

    @Override
    @Loggable
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    @Loggable
    @Transactional
    public User getUserByVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    @Loggable
    @Transactional
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    @Loggable
    @Transactional
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = verificationTokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(vToken);
        return vToken;
    }

    @Override
    @Loggable
    @Transactional
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    @Loggable
    @Transactional
    public void createPasswordResetTokenForUser(final UserDto userDto, final String token) {
        User user = mapperUtil.mapUserDtoToUser(userDto);
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    @Loggable
    @Transactional
    public void changeUserPassword(final Long id, final String password) {
        User byId = userRepository.findById(id).get();
        byId.setPassword(passwordEncoder.encode(password));
        userRepository.save(byId);
    }

    @Override
    @Loggable
    @Transactional
    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user: users) {
            UserDto userDto = mapperUtil.mapUserToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapperUtil.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoById(long id) {
        User user = userRepository.findById(id).get();
        return mapperUtil.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto editUser(UserDto userDto) {
        User user = mapperUtil.mapUserDtoToUser(userDto);
        userRepository.save(user);
        return userDto;
    }



// ============== NON-API ============

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
