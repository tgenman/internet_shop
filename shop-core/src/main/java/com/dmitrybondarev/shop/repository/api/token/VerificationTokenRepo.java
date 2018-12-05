package com.dmitrybondarev.shop.repository.api.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.repository.api.GenericRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends GenericRepo<VerificationToken> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
