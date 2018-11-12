package com.dmitrybondarev.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        log.info("showHomePage request");
        return "/home.jsp";
    }

    @GetMapping("/admin")
    public String showAdminPanel() {
        log.info("showAdminPanel request");
        return "/admin/adminPanel.jsp";
    }
}
