package com.dmitrybondarev.shop.web.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.dmitrybondarev.shop")
@PropertySource("classpath:application.properties")
@Import({MvcConfig.class,
        JpaConfig.class,
        SecurityConfig.class})
public class AppConfig {

    private Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DozerBeanMapper getDozerBeanMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.mail.port"))));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", Objects.requireNonNull(env.getProperty("spring.mail.protocol")));
//        props.put("mail.smtp.auth", Objects.requireNonNull(env.getProperty("spring.mail.smtp.auth")));
//        props.put("mail.smtp.starttls.enable", Objects.requireNonNull(env.getProperty("spring.mail.smtp.starttls.enable")));
        props.put("mail.debug", Objects.requireNonNull(env.getProperty("spring.mail.debug")));

        return mailSender;
    }

}
