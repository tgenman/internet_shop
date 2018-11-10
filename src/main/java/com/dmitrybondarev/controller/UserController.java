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

    @GetMapping
    public ModelAndView showMyUserEditForm() {
        log.info("user showMyUserEditForm start");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("input principal: " + user.toString());

        UserDto userDtoByUsername = userService.getUserDtoById(user.getId());

        log.info("input userDtoByUsername: " + userDtoByUsername.toString());

        ModelAndView mAV = new ModelAndView("user/editProfile.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("actualRoles", user.getRoles());
        mAV.addObject("roles", Role.values());

        log.info("user showMyUserEditForm end");
        return mAV;
    }

    @PostMapping
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {

        log.info("editUser start");
        log.info("input userDto: " + userDto.toString());
        long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        userDto.setId(id);

        if (result.hasErrors()) {
            log.info("editUser. Error in form");
            return new ModelAndView("redirect:/user", "productDto", userDto);
        } else {
            userService.editUser(userDto);
            log.info("editUser. Everything is ok. redirect");
            return new ModelAndView("redirect:/");
        }
    }


//    @GetMapping("/user/address/new")  //TODO Implement
//    @PostMapping("/user/address/new")  //TODO Implement
//    @GetMapping("/user/address/{id}")  //TODO Implement
//    @PostMapping("/user/address/{id}")  //TODO Implement
//    @DeleteMapping("/user/address/{id}")  //TODO Implement



}
