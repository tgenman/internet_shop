package com.dmitrybondarev.repo.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.repo.api.UserRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepoImp implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    @Override
    public boolean remove(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
            return true;
        }
        return  false;
    }

    @Override
    public boolean removeById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }
}
