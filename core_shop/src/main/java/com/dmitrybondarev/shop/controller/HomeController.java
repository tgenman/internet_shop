package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    @Loggable
    public String showHomePage() {
        return "/home.jsp";
    }

    @GetMapping("/admin")
    @Loggable
    public String showAdminPanel() {
        return "/admin/adminPanel.jsp";
    }
}
