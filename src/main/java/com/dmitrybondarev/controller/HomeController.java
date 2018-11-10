package com.dmitrybondarev.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        log.info("showHomePage request");
        return "/home.jsp";
    }
}
