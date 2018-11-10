package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Log4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView showMyUserEditForm() {
        log.info("user showMyUserEditForm start");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDtoByUsername = userService.getUserDtoById(user.getId());

        ModelAndView mAV = new ModelAndView("user/editProfile.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("actualRoles", user.getRoles());
        mAV.addObject("roles", Role.values());

        log.info("user showMyUserEditForm end");
        return mAV;
    }

    @PostMapping("/user")
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {  //TODO Implement editing User in user
        return new ModelAndView("/home.jsp");
    }


//    @GetMapping("/user/address")  //TODO Implement
//    @PostMapping("/user/address")  //TODO Implement
//    @GetMapping("/user/address/{id}")  //TODO Implement
//    @PostMapping("/user/address/{id}")  //TODO Implement
//    @DeleteMapping("/user/address/{id}")  //TODO Implement



}
