package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.dao.MockUserDataBase;
import com.dmitrybondarev.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImp implements UserService {

    @Autowired
    private MockUserDataBase mockUserDataBase;


}
