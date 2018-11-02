package com.dmitrybondarev.repo.api;

import com.dmitrybondarev.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepo {

    void addClient(User user);

    User findByUsername(String email);

    List<User> getAllClients();
}
