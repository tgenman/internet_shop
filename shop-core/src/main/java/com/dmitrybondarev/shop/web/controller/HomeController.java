package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Loggable
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/product";
    }

    @Loggable
    @GetMapping("/admin")
    public String showAdminPanel() {
        return "admin/adminPanel";
    }
}
