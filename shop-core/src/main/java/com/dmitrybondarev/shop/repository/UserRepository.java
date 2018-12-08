package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
