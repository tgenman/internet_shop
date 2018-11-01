package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.ClientDto;
import com.dmitrybondarev.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String register(ClientDto clientDto, Model model) {

        boolean check = clientService.registerNewClient(clientDto);

        if (check) return "redirect:/client/home";

        model.addAttribute("message", "User exists!");
        return "redirect:/client/registration";
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
