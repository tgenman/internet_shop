package com.dmitrybondarev.repository.impl;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.repository.api.UserRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Log4j
public class UserRepoImp implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User saveUser(User user) {
        log.info("Saving start. User(username=" + user.getUsername()
                + ", role="  + user.getRoles()
                + " firstName/family=" + user.getFirstName() + " " + user.getLastName());
        entityManager.persist(user);
        log.info("Saving end. Successful");
        return user;
    }

    @Override
    public User findById(long id) {
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
        User singleResult;
        try {
            singleResult = query.getSingleResult();
            log.info("Finding end. User found");
        } catch (NoResultException e) {
            log.warn("Finding end. User doesnt find");
            return null;
        }
        return singleResult;
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        User singleResult;
        try {
            singleResult = query.getSingleResult();  //TODO Delete Duplication
            log.info("Finding end. User found");
        } catch (NoResultException e) {
            log.warn("Finding end. User doesnt find");
            return null;
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
    public boolean removeById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        log.info("updateUser start");
        log.info("input: " + user.toString());
        User merge = entityManager.merge(user);
        log.info("output: " + merge.toString());
        log.info("updateUser end");
    }
}
