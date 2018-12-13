package com.dmitrybondarev.shop.repository.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByTokenContains(String token);

    VerificationToken findByUser(User user);
}
