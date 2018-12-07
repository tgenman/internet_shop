package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.dto.PasswordDto;
import com.dmitrybondarev.shop.model.token.VerificationToken;
import com.dmitrybondarev.shop.security.GenericResponse;
import com.dmitrybondarev.shop.security.UserSecurityService;
import com.dmitrybondarev.shop.security.event.OnRegistrationCompleteEvent;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.UUID;

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

    @Autowired
    private UserSecurityService userSecurityService;


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
        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUserByVerificationToken(newToken.getToken());
        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

    @Loggable
    @PostMapping("/user/resetPassword")
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final UserDto userDto = userService.getUserDtoByEmail(userEmail);
        if (userDto != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(userDto, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, userDto));
        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    @Loggable
    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = userSecurityService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @PostMapping("/user/savePassword")
    @ResponseBody
    public GenericResponse savePassword(Locale locale, @Valid PasswordDto passwordDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.changeUserPassword(user.getId(), passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
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

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final UserDto userDto) {
        final String url = contextPath + "/user/changePassword?id=" + userDto.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, userDto);
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserDto userDto) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(userDto.getEmail());
        email.setFrom("twidder.bot@yandex.ru");
        return email;
    }
}
