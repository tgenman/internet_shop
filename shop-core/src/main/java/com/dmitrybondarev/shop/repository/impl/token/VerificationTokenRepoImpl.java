package com.dmitrybondarev.shop.repository.impl.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.repository.api.token.VerificationTokenRepo;
import com.dmitrybondarev.shop.repository.impl.GenericRepoImpl;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class VerificationTokenRepoImpl extends GenericRepoImpl<VerificationToken> implements VerificationTokenRepo {

    @Override
    @Loggable
    public VerificationToken findByToken(String token) {
        TypedQuery<VerificationToken> query = entityManager.createQuery("select t from VerificationToken t where t.token = :token", VerificationToken.class);
        query.setParameter("token", token);
        VerificationToken singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    @Override
    @Loggable
    public VerificationToken findByUser(User user) {
        TypedQuery<VerificationToken> query = entityManager.createQuery("select t from VerificationToken t where t.user = :user", VerificationToken.class);
        query.setParameter("user", user);
        VerificationToken singleResult;
        try {                                               //TODO Fix
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }
}
