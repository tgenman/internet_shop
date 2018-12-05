package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private AddressService addressService;

    private static final String REDIRECT_TO_PROFILE = "redirect:/user";

    private static final String ADDRESS_DTO = "addressDto";

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @ModelAttribute("allRoles")
    public List<Role> populateTypes() {
        return Arrays.asList(Role.values());
    }

    @Loggable
    @GetMapping
    public String showMyUserEditForm(HttpServletRequest request, Model model) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto userDtoByUsername = userService.getUserDtoByEmail(principal.getUsername());
        userDtoByUsername.setPassword("");

        request.getSession().setAttribute("oldUserDto", userDtoByUsername);

        model.addAttribute("userDto", userDtoByUsername);
        model.addAttribute("actualRoles", userDtoByUsername.getRoles());
        model.addAttribute("roles", Role.values());

        return "user/editProfile";
    }

    @Loggable
    @PostMapping
    public String editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, HttpServletRequest request, Model model) {

        UserDto oldUserDto = (UserDto) request.getSession().getAttribute("oldUserDto");

        userDto.setId(oldUserDto.getId());
        userDto.setEnabled(oldUserDto.isEnabled());
        userDto.setAddresses(oldUserDto.getAddresses());

        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return REDIRECT_TO_PROFILE;
        } else {
            userService.editUser(userDto);
            return "redirect:/";
        }
    }

    @Loggable
    @GetMapping("/address/new")
    public String showAddressCreationForm(Model model) {
        model.addAttribute(ADDRESS_DTO, new AddressDto());
        return "/user/address/newAddress";
    }

    @Loggable
    @PostMapping("/address/new")
    public String addNewAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(ADDRESS_DTO, addressDto);
            return "/user/address/newAddress";
        } else {
            long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            addressService.addNewAddress(addressDto, id);
            return REDIRECT_TO_PROFILE;
        }
    }


    @Loggable
    @GetMapping("/address/{id}")
    public String showAddressEditForm(@PathVariable long id, HttpServletRequest request, Model model) {
        AddressDto addressDto = addressService.getAddressDtoById(id);
        request.getSession().setAttribute("oldAddressDto", addressDto);
        model.addAttribute(ADDRESS_DTO, addressDto);
        return "/user/address/editAddress";
    }

    @Loggable
    @PostMapping("/address/{id}")
    public String editAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                    BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(ADDRESS_DTO, addressDto);
            return "/user/address/editAddress";
        } else {
            AddressDto oldAddressDto = (AddressDto) request.getSession().getAttribute("oldAddressDto");
            addressDto.setId(oldAddressDto.getId());
            addressService.editAddress(addressDto);
            return REDIRECT_TO_PROFILE;
        }
    }

//    @DeleteMapping("/address/{id}")   TODO FIX DELETING
//    public String removeAddress(@PathVariable long id) {
//        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//        addressService.deleteAddress(id, idUser);
//        return "redirect:/user/";
//    }
}
