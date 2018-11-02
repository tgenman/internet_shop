package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@Log4j
public class UserController {

//    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        log.info("Registration GET request");
        return "user/registration";
    }

    @PostMapping("/registration")
    public String register(UserDto userDto, Model model) {

        log.info("Registry POST request:"
                + " name = " + userDto.getName()
                + " familyName = " + userDto.getFamilyName()
                + " email = " + userDto.getEmail());

        boolean check = userService.registerNewClient(userDto);

        if (check) {
            log.info("User with username = " + userDto.getEmail() + " already exists");
            return "redirect:/user/login";
        } else {
            model.addAttribute("message", "User exists!");
            log.info("User was register ");
            return "redirect:/user/registration";
        }

    }

    @PostMapping("/logout")
    public String logout() {
        log.info("User logout.");
        return "home";
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        log.info("Get all users GET request");
        return new ModelAndView("/user/allClients")
                .addObject("clients", userService.getAllClients());
    }
}
