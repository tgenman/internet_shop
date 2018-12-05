package com.dmitrybondarev.shop.repository.api.token;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.PasswordResetToken;
import com.dmitrybondarev.shop.repository.api.GenericRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends GenericRepo<PasswordResetToken> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);
}
