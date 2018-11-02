package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    Collection<User> getAllClients();

    boolean registerNewClient(UserDto userDto);
}
