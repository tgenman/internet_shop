package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.model.VerificationToken;
import com.dmitrybondarev.shop.security.GenericResponse;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
//@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;


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
            model.addAttribute("message", messages.getMessage("auth.message.invalidToken", null, locale));
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        userService.enableUser(user);
        model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @Loggable
    @GetMapping("/user/resendRegistrationToken")
    @ResponseBody
    public GenericResponse resendRegistrationToken(      //TODO Move Logic to Service
            HttpServletRequest request, @RequestParam("token") String existingToken) {
        VerificationToken newToken = userService.generateNewVerificationToken(existingToken);

        User user = userService.getUserByVerificationToken(newToken.getToken());
        String appUrl =
                "http://" + request.getServerName() +
                        ":" + request.getServerPort() +
                        request.getContextPath();
        SimpleMailMessage email =
                constructResendVerificationTokenEmail(appUrl, request.getLocale(), newToken, user);
        mailSender.send(email);

        return new GenericResponse(
                messages.getMessage("message.resendToken", null, request.getLocale()));
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

    private SimpleMailMessage constructResendVerificationTokenEmail    //TODO Move to Service
            (String contextPath, Locale locale, VerificationToken newToken, User user) {
        String confirmationUrl =
                contextPath + "/regitrationConfirm.html?token=" + newToken.getToken();
        String message = messages.getMessage("message.resendToken", null, locale);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Resend Registration Token");
        email.setText(message + " rn" + confirmationUrl);
        email.setFrom("twidder.bot@yandex.ru");
        email.setTo(user.getEmail());
        return email;
    }
}
