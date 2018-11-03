package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        log.info("Registration GET request");
        return "user/registration";
    }

    @PostMapping("/registration")
    public ModelAndView register(UserDto userDto) {

        log.info("Registry POST request:" + " email = " + userDto.getEmail());

        boolean check = userService.registerNewUser(userDto);

        if (check) {
            log.info("User with username = " + userDto.getEmail() + " was register.");
            return new ModelAndView("/user/login");

        } else {
            ModelAndView modelAndView = new ModelAndView("/user/registration");
            modelAndView.addObject("existMessage", "User exists!");
            log.info("User with username = " + userDto.getEmail() + " already exists.");
            return modelAndView;
        }

    }

    @PostMapping("/logout")
    public String logout(@AuthenticationPrincipal User user) {
        log.info("User logout. Username = " + user.getUsername());
        return "home";
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        log.info("Get all users GET request");
        return new ModelAndView("/user/allUsers")
                .addObject("users", userService.getAllUsers());
    }
}
