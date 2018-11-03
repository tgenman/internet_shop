package com.dmitrybondarev.repositories.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.repositories.api.UserRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Log4j
public class UserRepoImp implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        log.info("Saving start. User(username=" + user.getUsername()
                + ", role="  + user.getRoles()
                + " name/family=" + user.getName() + " " + user.getFamilyName());
        entityManager.persist(user);
        log.info("Saving end. Successful");
    }

    @Override
    public User findById(int id) {
        log.info("Finding User by id = " + id + " start.");
        User user = entityManager.find(User.class, id);
        if (user == null) {
            log.warn("Finding end. User doesnt find");
        } else {
            log.info("Finding end. User = " + user.getUsername());
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        log.info("Finding User by username = " + username + " start.");
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        User singleResult = query.getSingleResult();
        if (singleResult == null) {
            log.warn("Finding end. User doesnt find");
        } else {
            log.info("Finding end. User = " + singleResult.getId());
        }
        return singleResult;
    }

    @Override
    public List<User> findAll() {
        log.info("Finding all. Starting");
        List<User> users = entityManager.createQuery("select user from User user", User.class).getResultList();
        log.info("Finding all. end");
        return users;
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
