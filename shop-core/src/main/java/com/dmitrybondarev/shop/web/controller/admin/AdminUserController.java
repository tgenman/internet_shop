package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    private UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @Loggable
    @GetMapping
    public String showListOfAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("allUserDto", userService.getAllUsers());
        return "admin/user/userList";
    }

    @Loggable
    @GetMapping("/{id}")
    public String showUserEditForm(@PathVariable long id, Model model) {
        UserDto userDtoByUsername = userService.getUserDtoById(id);

        model.addAttribute("userDto", userDtoByUsername);
        model.addAttribute("roles", Role.values());

        return "admin/user/userEdit";
    }

    @PostMapping("/{id}")
    @Loggable
    public String editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors) {  //TODO Implement editing User in admin
        return "index";
    }

    @DeleteMapping("/{id}")
    @Loggable
    public String deleteUser(@PathVariable long id) {  //TODO Implement deleting User

        return "index";
    }


}
