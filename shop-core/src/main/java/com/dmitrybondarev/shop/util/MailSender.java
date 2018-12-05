package com.dmitrybondarev.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

}
