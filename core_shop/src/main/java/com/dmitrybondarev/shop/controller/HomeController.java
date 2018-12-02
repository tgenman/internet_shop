package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Loggable
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @Loggable
    @GetMapping("/admin")
    public String showAdminPanel() {
        return "admin/adminPanel";
    }

}
