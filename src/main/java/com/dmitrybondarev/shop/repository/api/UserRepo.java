package com.dmitrybondarev.shop.repository.api;

import com.dmitrybondarev.shop.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends GenericRepo<User> {

    User findByUsername(String email);
}
