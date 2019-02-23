package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);


//    @Query("SELECT u FROM user u WHERE u.id = ?1")
//    User findUserById1(long status);

}
