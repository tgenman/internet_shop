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
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @GetMapping("/{id}")
//    public String getProfilePage(@PathVariable int id, Model model) {
//        return "cliet/profile";
//    }
//
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        Collection<Client> allClients = clientService.getAllClients();
//        if (allClients.isEmpty()) return "home";
//        Client next = allClients.iterator().next();
        model.addAttribute("clients", allClients);
        model.addAttribute("var", "somevar");
        return "/client/allClients";
    }
}
