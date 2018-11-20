package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.service.api.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Loggable
    @GetMapping
    public ModelAndView showMyUserEditForm(HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto userDtoByUsername = userService.getUserDtoById(user.getId());
        userDtoByUsername.setPassword("");

        request.getSession().setAttribute("oldUserDto", userDtoByUsername);

        ModelAndView mAV = new ModelAndView("user/editProfile.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("actualRoles", user.getRoles());
        mAV.addObject("roles", Role.values());

        return mAV;
    }

    @Loggable
    @PostMapping
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors, HttpServletRequest request) {

        UserDto oldUserDto = (UserDto) request.getSession().getAttribute("oldUserDto");

        userDto.setId(oldUserDto.getId());
        userDto.setActive(oldUserDto.isActive());
        userDto.setAddresses(oldUserDto.getAddresses());

        if (result.hasErrors()) {
            return new ModelAndView("redirect:/user", "productDto", userDto);
        } else {
            userService.editUser(userDto);
            return new ModelAndView("redirect:/");
        }
    }


    @Loggable
    @GetMapping("/address/new")
    public ModelAndView showAddressCreationForm() {
        return new ModelAndView("/user/address/newAddress.jsp", "addressDto", new AddressDto());
    }

    @Loggable
    @PostMapping("/address/new")
    public ModelAndView addNewAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                      BindingResult result, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("/user/address/newAddress.jsp", "addressDto", addressDto);
        } else {
            long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            addressService.addNewAddress(addressDto, id);
            return new ModelAndView("redirect:/user/");
        }
    }


    @Loggable
    @GetMapping("/address/{id}")
    public ModelAndView showMyUserEditForm(@PathVariable long id, HttpServletRequest request) {
        AddressDto addressDto = addressService.getAddressDtoById(id);
        request.getSession().setAttribute("oldAddressDto", addressDto);
        ModelAndView mAV = new ModelAndView("/user/address/editAddress.jsp");
        mAV.addObject("addressDto", addressDto);
        return mAV;
    }

    @Loggable
    @PostMapping("/address/{id}")
    public ModelAndView editAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                    BindingResult result, Errors errors, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ModelAndView("/user/address/editAddress.jsp", "addressDto", addressDto);
        } else {
            AddressDto oldAddressDto = (AddressDto) request.getSession().getAttribute("oldAddressDto");
            addressDto.setId(oldAddressDto.getId());
            addressService.editAddress(addressDto);
            return new ModelAndView("redirect:/user/");
        }
    }

    @DeleteMapping("/address/{id}")
    public String removeAddress(@PathVariable long id) {
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        addressService.deleteAddress(id, idUser);
        return "redirect:/user/";
    }



}
