package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserRepoImp extends GenericRepoImpl<User> implements UserRepo {

    public UserRepoImp() {
        setClazz(User.class);
    }


    @Override
    @Loggable
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        User singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("You cant delete User from DB. Use inactivation");
    }

    @Override
    public void deleteById(Long entityId) {
        throw new UnsupportedOperationException("You cant delete User from DB. Use inactivation");
    }
}
