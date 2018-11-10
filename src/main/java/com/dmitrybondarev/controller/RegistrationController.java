package com.dmitrybondarev.controller;

import com.dmitrybondarev.exception.EmailExistsException;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        log.info("registerUserAccount");
        User user = new User();
        if (!result.hasErrors()) {
            log.info("There are not errors. Start registration");
            userDto.setActive(true);
            userDto.setRoles(Collections.singleton(Role.USER));
            user = createUserAccount(userDto, result);
        }
        if (user == null) {
            log.info("Check error username existence");
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            log.info("There is error");
            return new ModelAndView("user/registration", "user", userDto);
        }
        else {
            log.info("Registration complete.");
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
