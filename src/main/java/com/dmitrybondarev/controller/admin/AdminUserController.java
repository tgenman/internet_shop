package com.dmitrybondarev.controller.admin;

import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView showListOfAllUsers() {
        log.info("admin showListOfAllUsers start");
        ModelAndView mAV = new ModelAndView("admin/user/userList.jsp", "userDtos", userService.getAllUsers());
        log.info("admin showListOfAllUsers end");
        return mAV;
    }

    @GetMapping("/{id}")
    public ModelAndView showUserEditForm(@PathVariable long id) {
        log.info("admin showMyselfUserEditForm start");
        UserDto userDtoByUsername = userService.getUserDtoById(id);

        ModelAndView mAV = new ModelAndView("admin/user/userEdit.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("roles", Role.values());

        log.info("admin showMyselfUserEditForm end");
        return mAV;
    }

    @PostMapping("/{id}")
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {  //TODO Implement editing User in admin
        return new ModelAndView("/home.jsp");
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {  //TODO Implement deleting User

        return new ModelAndView("/home.jsp");
    }


}
