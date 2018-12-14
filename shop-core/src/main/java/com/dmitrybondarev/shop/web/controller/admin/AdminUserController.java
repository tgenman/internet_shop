package com.dmitrybondarev.shop.web.controller.admin;

import com.dmitrybondarev.shop.util.logging.Loggable;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        model.addAttribute("allUserDto", userService.getAllUsers());
        return "admin/user/userList";
    }

    @Loggable
    @GetMapping("/{email}")
    public String showUserEditForm(@PathVariable String email,
                                   Model model,
                                   HttpSession httpSession) {
        httpSession.removeAttribute("idUserForEdit");
        httpSession.setAttribute("emailUserForEdit", email);

        UserDto userDtoByUsername = userService.getUserDtoByEmail(email);

        model.addAttribute("userDto", userDtoByUsername);
        model.addAttribute("roles", Role.values());

        return "admin/user/userEdit";
    }

    @Loggable
    @PostMapping
    public String editUser(@Valid UserDto userDto,
                           BindingResult result,
                           HttpServletRequest request,
                           Model model) {
        String emailUserForEdit = (String) request.getSession().getAttribute("emailUserForEdit");
        userDto.setEmail(emailUserForEdit);

        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "/admin/category/"+ userDto.getId();
        }

        userService.editUser(userDto);

        return "admin/user/userEdit";
    }
}
