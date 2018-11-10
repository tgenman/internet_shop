package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo {

    User save(User user);

    User findById(long id);

    User findByUsername(String email);

    User findByEmail(String email);

    List<User> findAll();

    boolean remove(User user);

    boolean removeById(long id);

    void updateUser(User user);
}
