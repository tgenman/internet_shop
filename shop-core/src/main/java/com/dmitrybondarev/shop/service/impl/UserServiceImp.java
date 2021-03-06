package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.token.PasswordResetToken;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.repository.token.PasswordResetTokenRepository;
import com.dmitrybondarev.shop.repository.token.VerificationTokenRepository;
import com.dmitrybondarev.shop.service.api.UserService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.util.exception.UserNotFoundException;
import com.dmitrybondarev.shop.util.exception.VerificationTokenNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent())
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());

        User user = mapperUtil.mapUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList("ROLE_USER"));
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
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByTokenContains(verificationToken);
        if (!optionalToken.isPresent()) throw new VerificationTokenNotFoundException("Verification token not found by String: " + verificationToken);
        return optionalToken.get().getUser();
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
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByTokenContains(existingVerificationToken);
        if (!optionalToken.isPresent()) throw new VerificationTokenNotFoundException("Verification token not found by String: " + existingVerificationToken);
        VerificationToken vToken = optionalToken.get();

        vToken.updateToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(vToken);
        return vToken;
    }

    @Override
    @Loggable
    @Transactional
    public VerificationToken getVerificationToken(String verificationToken) {
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByTokenContains(verificationToken);
        if (!optionalToken.isPresent()) throw new VerificationTokenNotFoundException("Verification token not found by String: " + verificationToken);
        return optionalToken.get();
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
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("No user found with id: "+ id);
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
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
    public UserDto getUserDtoByEmail(String userEmail) {
        User user = this.pullOutUserFromRepositoryByEmail(userEmail);

        Set<Address> addresses = user.getAddresses();
        addresses.stream().filter(Address::isActive).collect(Collectors.toSet());
        user.setAddresses(addresses);

        return mapperUtil.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto getUserDtoById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("No user found with id: "+ id);
        User user = optionalUser.get();
        return mapperUtil.mapUserToUserDto(user);
    }

    @Override
    @Loggable
    @Transactional
    public UserDto editUser(UserDto userDto) {
        User user = this.pullOutUserFromRepositoryByEmail(userDto.getEmail());

        user.setRoles(userDto.getRoles());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        userRepository.save(user);
        return userDto;
    }


//  ============== NON-API =================

    @Loggable
    private User pullOutUserFromRepositoryByEmail(String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("No category found with email: " + userEmail);
        return optionalUser.get();
    }
}
