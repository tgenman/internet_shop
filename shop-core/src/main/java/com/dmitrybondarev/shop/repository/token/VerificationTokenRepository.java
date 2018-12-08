package com.dmitrybondarev.shop.repository.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
