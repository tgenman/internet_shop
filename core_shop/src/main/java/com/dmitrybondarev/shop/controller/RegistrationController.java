package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Loggable
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/registration";
    }

    @Loggable
    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @Valid UserDto userDto,
            BindingResult result) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("user/registration", "userDto", userDto);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

//        User user = new User();
//        if (!result.hasErrors()) {
//            userDto.setActive(true);
//            userDto.setRoles(Collections.singleton(Role.USER));
//            user = createUserAccount(userDto, result);
//        }
//        if (user == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if (result.hasErrors()) {
//            model.addAttribute("userDto", userDto);
//            return "user/registration";
//        }
//        else {
//            return "redirect:/login";
//        }
//    }

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
}
