package com.dmitrybondarev.repo.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.repo.api.UserRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepoImp implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addClient(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly=true)
    public List<User> getAllClients() {
        return entityManager.createQuery("select user from usr user").getResultList();
    }

    @Transactional(readOnly=true)
    public User findByEmail(String email) {

//        boolean result = entityManager.createQuery("select user from User user where user = email")
//                .getResultList().isEmpty();
//
//
//        if (result) return true;
        return null;

    }

}
