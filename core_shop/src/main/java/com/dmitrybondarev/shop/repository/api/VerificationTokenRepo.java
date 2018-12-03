package com.dmitrybondarev.shop.repository.api;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.VerificationToken;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends GenericRepo<VerificationToken> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
