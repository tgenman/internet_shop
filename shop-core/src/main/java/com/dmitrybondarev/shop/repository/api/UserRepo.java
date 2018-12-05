package com.dmitrybondarev.shop.repository.api;

import com.dmitrybondarev.shop.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends GenericRepo<User> {

    User findByEmail(String email);
}
