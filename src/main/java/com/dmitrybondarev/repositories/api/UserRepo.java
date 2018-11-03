package com.dmitrybondarev.repositories.api;

import com.dmitrybondarev.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepo {

    void save(User user);

    User findById(int id);

    User findByUsername(String email);

    List<User> findAll();

    boolean remove(User user);

    boolean removeById(int id);
}
