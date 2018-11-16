package com.dmitrybondarev.shop.controller.admin;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.UserService;
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

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Loggable
    public ModelAndView showListOfAllUsers() {
        return new ModelAndView("admin/user/userList.jsp", "userDtos", userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Loggable
    public ModelAndView showUserEditForm(@PathVariable long id) {
        UserDto userDtoByUsername = userService.getUserDtoById(id);

        ModelAndView mAV = new ModelAndView("admin/user/userEdit.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("roles", Role.values());

        return mAV;
    }

    @PostMapping("/{id}")
    @Loggable
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {  //TODO Implement editing User in admin
        return new ModelAndView("/home.jsp");
    }

    @DeleteMapping("/{id}")
    @Loggable
    public ModelAndView deleteUser(@PathVariable long id) {  //TODO Implement deleting User

        return new ModelAndView("/home.jsp");
    }


}
