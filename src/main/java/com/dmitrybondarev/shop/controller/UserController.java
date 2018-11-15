package com.dmitrybondarev.shop.controller;

import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.service.api.UserService;
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

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView showMyUserEditForm(HttpServletRequest request) {
        log.info("user showMyUserEditForm start");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("input principal: " + user.toString());

        UserDto userDtoByUsername = userService.getUserDtoById(user.getId());
        userDtoByUsername.setPassword("");

        log.info("input userDtoByUsername: " + userDtoByUsername.toString());

        request.getSession().setAttribute("oldUserDto", userDtoByUsername);

        ModelAndView mAV = new ModelAndView("user/editProfile.jsp");
        mAV.addObject("userDto", userDtoByUsername);
        mAV.addObject("actualRoles", user.getRoles());
        mAV.addObject("roles", Role.values());

        log.info("user showMyUserEditForm end");
        return mAV;
    }

    @PostMapping
    public ModelAndView editUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 BindingResult result, Errors errors, HttpServletRequest request) {

        log.info("editUser start");
        log.info("input userDto: " + userDto.toString());
        UserDto oldUserDto = (UserDto) request.getSession().getAttribute("oldUserDto");
        log.info("oldUserDto: " + oldUserDto.toString());

        userDto.setId(oldUserDto.getId());
        userDto.setActive(oldUserDto.isActive());
        userDto.setAddresses(oldUserDto.getAddresses());

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
    public ModelAndView showMyUserEditForm(@PathVariable long id, HttpServletRequest request) {
        log.info("showMyUserEditForm start");
        AddressDto addressDto = addressService.getAddressDtoById(id);
        request.getSession().setAttribute("oldAddressDto", addressDto);
        ModelAndView mAV = new ModelAndView("/user/address/editAddress.jsp");
        mAV.addObject("addressDto", addressDto);
        log.info("showMyUserEditForm end");
        return mAV;
    }

    @PostMapping("/address/{id}")
    public ModelAndView editAddress(@ModelAttribute("addressDto") @Valid AddressDto addressDto,
                                    BindingResult result, Errors errors, HttpServletRequest request) {
        log.info("editAddress start");

        if (result.hasErrors()) {
            log.info("editAddress. Error in form");
            return new ModelAndView("/user/address/editAddress.jsp", "addressDto", addressDto);
        } else {
            AddressDto oldAddressDto = (AddressDto) request.getSession().getAttribute("oldAddressDto");
            addressDto.setId(oldAddressDto.getId());
            addressService.editAddress(addressDto);
            log.info("editAddress. Everything is ok. redirect");
            return new ModelAndView("redirect:/user/");
        }
    }

    @DeleteMapping("/address/{id}")
    public String removeAddress(@PathVariable long id) {
        log.info("removeAddress start");
        long idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        addressService.deleteAddress(id, idUser);
        log.info("removeAddress end");
        return "redirect:/user/";
    }



}
