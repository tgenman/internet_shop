package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.model.dto.UserEditForm;
import com.dmitrybondarev.shop.util.exception.AddressNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.service.api.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private AddressService addressService;

    private static final String REDIRECT_TO_PROFILE = "redirect:/user";

    private static final String ADDRESS_DTO = "addressDto";

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
    public String showMyUserEditForm(@AuthenticationPrincipal UserDetails userDetails,
                                     HttpServletRequest request,
                                     Model model) {

        UserDto userDto = userService.getUserDtoByEmail(userDetails.getUsername());

        UserEditForm userEditForm = new UserEditForm();
        userEditForm.setFirstName(userDto.getFirstName());
        userEditForm.setLastName(userDto.getLastName());
        userEditForm.setDateOfBirth(userDto.getDateOfBirth());

        request.getSession().setAttribute("oldUserDto", userDto);

        model.addAttribute("userEditForm", userEditForm);
        model.addAttribute("addresses", addressService.getAllActiveAddressByUserEmail(userDetails.getUsername()));
        return "user/editProfile";
    }

    @Loggable
    @PostMapping
    public String editUser(@Valid UserEditForm userEditForm,
                           BindingResult result,
                           HttpServletRequest request,
                           Model model) {

        if (result.hasErrors()) {
            model.addAttribute("userDto", userEditForm);
            return REDIRECT_TO_PROFILE;
        }

        UserDto oldUserDto = (UserDto) request.getSession().getAttribute("oldUserDto");

        oldUserDto.setFirstName(userEditForm.getFirstName());
        oldUserDto.setLastName(userEditForm.getLastName());
        oldUserDto.setDateOfBirth(userEditForm.getDateOfBirth());

        userService.editUser(oldUserDto);
        return "redirect:/";
    }

    @Loggable
    @GetMapping("/address/new")
    public String showAddressCreationForm(Model model) {
        model.addAttribute(ADDRESS_DTO, new AddressDto());
        return "/user/address/newAddress";
    }

    @Loggable
    @PostMapping("/address/new")
    public String addNewAddress(@Valid AddressDto addressDto,
                                BindingResult result,
                                @AuthenticationPrincipal UserDetails userDetails,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute(ADDRESS_DTO, addressDto);
            return "/user/address/newAddress";
        }

        addressDto.setActive(true);
        addressService.addNewAddress(addressDto, userDetails.getUsername());
        return REDIRECT_TO_PROFILE;
    }


    @Loggable
    @GetMapping("/address/{id}")
    public String showAddressEditForm(@PathVariable long id,
                                      HttpServletRequest request,
                                      Model model) {
        AddressDto addressDto = addressService.getAddressDtoById(id);
        request.getSession().setAttribute("oldAddressDto", addressDto);
        model.addAttribute(ADDRESS_DTO, addressDto);
        return "/user/address/editAddress";
    }

    @Loggable
    @PostMapping("/address/{id}")
    public String editAddress(@Valid AddressDto addressDto,
                              BindingResult result,
                              HttpServletRequest request,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute(ADDRESS_DTO, addressDto);
            return "/user/address/editAddress";
        } else {
            AddressDto oldAddressDto = (AddressDto) request.getSession().getAttribute("oldAddressDto");
            addressDto.setId(oldAddressDto.getId());
            addressDto.setActive(true);
            addressService.editAddress(addressDto);
            return REDIRECT_TO_PROFILE;
        }
    }

    @Loggable
    @PostMapping("/address/{id}/delete")
    public String inactivateAddress(@PathVariable long id) {
        addressService.inactivateAddress(id);
        return REDIRECT_TO_PROFILE;
    }
}
