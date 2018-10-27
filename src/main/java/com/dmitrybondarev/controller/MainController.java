package com.dmitrybondarev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home() {
        System.out.println("HomeController: Passing through...");
        return "home";
    }
}
