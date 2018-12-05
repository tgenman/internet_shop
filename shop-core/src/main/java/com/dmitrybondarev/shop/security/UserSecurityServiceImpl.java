package com.dmitrybondarev.shop.security;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.token.PasswordResetToken;
import com.dmitrybondarev.shop.repository.api.token.PasswordResetTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;

@Service
@Transactional
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;

    @Override
    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = passwordResetTokenRepo.findByToken(token);
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }
}
