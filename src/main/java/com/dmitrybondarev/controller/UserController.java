package com.dmitrybondarev.controller;

import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.AddressDto;
import com.dmitrybondarev.model.dto.UserDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.service.api.AddressService;
import com.dmitrybondarev.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Log4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

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


    @GetMapping("/address/new")
    public ModelAndView showAddressCreationForm() {
        log.info("showAddressCreationForm start");
        ModelAndView mAV = new ModelAndView("/user/address/newAddress.jsp", "addressDto", new AddressDto());
        log.info("showAddressCreationForm end");
        return mAV;
    }

    @PostMapping("/address/new")
    public ModelAndView addNewAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                      BindingResult result, Errors errors) {
        log.info("addNewAddress start");
        if (result.hasErrors()) {
            log.info("addNewAddress. Error in form");
            return new ModelAndView("/user/address/newAddress.jsp", "addressDto", addressDto);
        } else {
            long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            addressService.addNewAddress(addressDto, id);
            log.info("addNewAddress. Everything is ok. redirect");
            return new ModelAndView("redirect:/user/");
        }
    }


    @GetMapping("/address/{id}")
    public ModelAndView showMyUserEditForm(@PathVariable long id) {
        log.info("showMyUserEditForm start");
        Address addressDto = addressService.getAddressDtoById(id);
        ModelAndView mAV = new ModelAndView("/user/address/editAddress.jsp");
        mAV.addObject("addressId", id);
        mAV.addObject("addressDto", addressDto);
        log.info("showMyUserEditForm end");
        return mAV;
    }

    @PostMapping("/address/{id}")
    public ModelAndView editAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                    BindingResult result, Errors errors, Model model) {
        log.info("editAddress start");

        if (result.hasErrors()) {
            log.info("editAddress. Error in form");
            return new ModelAndView("/user/address/editAddress.jsp", "addressDto", addressDto);
        } else {
//            TODO HOW find old id
            addressService.editAddress(addressDto);
            log.info("editAddress. Everything is ok. redirect");
            return new ModelAndView("redirect:/admin/product");
        }
    }

    @DeleteMapping("/address/{id}")
    public String removeAddress(@PathVariable long id) {
        log.info("removeAddress start");
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        addressService.deleteAddress(id, idUser);
        log.info("removeAddress end");
        return "redirect:/story/inventory";
    }



}
