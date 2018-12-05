package com.dmitrybondarev.shop.repository.impl.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.PasswordResetToken;
import com.dmitrybondarev.shop.repository.api.token.PasswordResetTokenRepo;
import com.dmitrybondarev.shop.repository.impl.GenericRepoImpl;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class PasswordResetTokenRepoImpl extends GenericRepoImpl<PasswordResetToken> implements PasswordResetTokenRepo {

    @Override
    @Loggable
    public PasswordResetToken findByToken(String token) {
        TypedQuery<PasswordResetToken> query = entityManager.createQuery("select t from PasswordResetToken t where t.token = :token", PasswordResetToken.class);
        query.setParameter("token", token);
        PasswordResetToken singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    @Override
    @Loggable
    public PasswordResetToken findByUser(User user) {
        TypedQuery<PasswordResetToken> query = entityManager.createQuery("select t from PasswordResetToken t where t.user = :user", PasswordResetToken.class);
        query.setParameter("user", user);
        PasswordResetToken singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }
}
