package com.dmitrybondarev.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.dmitrybondarev")
@Import({MvcConfig.class,
        JpaConfig.class,
        WebSecurityConfig.class})
public class AppConfig {
}
