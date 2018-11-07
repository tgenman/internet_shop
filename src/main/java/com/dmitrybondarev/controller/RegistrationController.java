package com.dmitrybondarev.controller;

import com.dmitrybondarev.exception.EmailExistsException;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
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

@Log4j
@Controller
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public ModelAndView showRegistrationForm(WebRequest request) {
        log.info("Registration GET request");
        return new ModelAndView("user/registration", "userDto", new UserDto());
    }


    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("userDto") @Valid UserDto userDto,
                                            BindingResult result, WebRequest request, Errors errors) {
        log.info("into post registration controller");
        User registered = new User();
        if (!result.hasErrors()) {
            log.info("1");
            registered = createUserAccount(userDto, result);
        }
        if (registered == null) {
            log.info("2");
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            log.info("3");
            return new ModelAndView("user/registration", "user", userDto);
        }
        else {
            log.info("4");
            return new ModelAndView("user/successRegister", "user", userDto);
        }
    }

// ============== NON-API ============


    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

//    @PostMapping("/registration")
//    public ModelAndView register(UserDto userDto) {
//
//        log.info("Registry POST request:" + " email = " + userDto.getEmail());
//
//        boolean check = userService.registerNewUser(userDto);
//
//        if (check) {
//            log.info("User with username = " + userDto.getEmail() + " was register.");
//            return new ModelAndView("/login");
//
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/registration");
//            modelAndView.addObject("existMessage", "User exists!");
//            log.info("User with username = " + userDto.getEmail() + " already exists.");
//            return modelAndView;
//        }
//    }
}
