package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String home(Model model) {
        Client client = new Client();
        client.setName("Ivan");
        model.addAttribute(client);
        return "home";
    }
}
