package com.dmitrybondarev.shop.security;

public interface UserSecurityService  {

    String validatePasswordResetToken(long id, String token);
}
