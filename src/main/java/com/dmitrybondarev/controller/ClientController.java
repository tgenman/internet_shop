package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "client/registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String name,
                           @RequestParam String familyName,
                           @RequestParam String email,
                           @RequestParam String password) {
        clientService.registerNewClient(name, familyName, email, password);
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return "home";
    }

    @PostMapping("/logout")
    public String logout() {
        return "home";
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        return new ModelAndView("/client/allClients")
                .addObject("clients", clientService.getAllClients());
    }
}
