package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.api.UserRepo;
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
public class UserRepoImp extends GenericRepoImpl<User> implements UserRepo {

    public UserRepoImp() {
        setClazz(User.class);
    }


    @Override
    public User findByUsername(String username) {
        log.info("Finding User by username = " + username + " start.");
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        User singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
            log.info("Finding end. User found");
        } catch (NoResultException e) {
            log.warn("Finding end. User doesnt find");
            return null;
        }
        return singleResult;
    }
}
