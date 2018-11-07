package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Log4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getAllUsers() {
        log.info("Get all users GET request");
        return new ModelAndView("/user/userList")
                .addObject("userDtos", userService.getAllUsers());
    }

//    @GetMapping("{id}")
//    public String userEditForm(@PathVariable(required = true) String username, Model model) {
//        UserDto userDtoByUsername = userService.getUserDtoByUsername(username);
//        model.addAttribute("userDto", userDtoByUsername);
//        model.addAttribute("roles", Role.values());
//        return "user/userEdit";
//    }
//
//    @PostMapping
//    public String saveUser(@RequestParam String username,
//                           @RequestParam Map<String, String> form) {
//        UserDto userDtoByUsername = userService.getUserDtoByUsername(username);
////TODO logic
//        return "redirect:/user";
//    }
}
