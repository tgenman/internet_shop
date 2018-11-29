package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    @Loggable
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/registration";
    }


    @PostMapping("/registration")
    @Loggable
    public ModelAndView registerUserAccount(@ModelAttribute("userDto") UserDto userDto,
                                            BindingResult result, WebRequest request, Errors errors) {
        User user = new User();
        if (!result.hasErrors()) {
            userDto.setActive(true);
            userDto.setRoles(Collections.singleton(Role.USER));
            user = createUserAccount(userDto, result);
        }
        if (user == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("user/registration", "user", userDto);
        }
        else {
            return new ModelAndView("redirect:/login");
        }
    }

// ============== NON-API ============


    private User createUserAccount(UserDto accountDto, BindingResult result) { //TODO Resolve by ExceptionHandler
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
