package com.dmitrybondarev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dmitrybondarev")
public class MVCConfig implements WebMvcConfigurer {

    private static final String PREFIX = "/WEB-INF/view/";
    private static final String SUFFIX = ".jsp";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(PREFIX);
        resolver.setSuffix(SUFFIX);
        return resolver;
    }
}
