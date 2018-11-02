package com.dmitrybondarev.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Log4j
public class HomeController {

//    private static final Logger log = Logger.getLogger(HomeController.class);

    @GetMapping
    public String getHomePage(HttpServletRequest request) {
        log.info("Home GET request");
        return "home";
    }
}
