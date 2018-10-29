package com.dmitrybondarev.controller;

import com.dmitrybondarev.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

//    @PostMapping
//    public String setRegistrationInfo(Model model) {
//        return "registration";
//    }
}
