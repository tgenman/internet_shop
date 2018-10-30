package com.dmitrybondarev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@PropertySource("classpath:mvc.properties")
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(env.getProperty("spring.view_resolver_prefix"));
        resolver.setSuffix(env.getProperty("spring.view_resolver_suffix"));
        return resolver;
    }
}