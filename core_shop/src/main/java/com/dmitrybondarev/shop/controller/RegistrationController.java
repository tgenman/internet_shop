package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.model.VerificationToken;
import com.dmitrybondarev.shop.security.OnRegistrationCompleteEvent;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.util.exception.EmailExistsException;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
//@RequestMapping("/user")
public class RegistrationController {

    private UserService userService;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messages;

    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher, MessageSource messages) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.messages = messages;
    }

    @Loggable
    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/registration";
    }

    @Loggable
    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @Valid UserDto userDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        if (result.hasErrors()) {
            return new ModelAndView("user/registration", "userDto", userDto);
        }

        User registered = createUserAccount(userDto, result);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
            return new ModelAndView("user/registration", "userDto", userDto);
        } else {
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            } catch (Exception me) {
                return new ModelAndView("emailError", "user", userDto);
            }
        }

        return new ModelAndView("redirect:/login");
    }


    @Loggable
    @GetMapping("/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        userService.enableUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
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
