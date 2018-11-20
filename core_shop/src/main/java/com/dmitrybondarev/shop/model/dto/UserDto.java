package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.enums.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dmitrybondarev.shop.validation.PasswordMatches;
import com.dmitrybondarev.shop.validation.ValidEmail;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@PasswordMatches
public class UserDto {

    private long id;

//    @NotNull
//    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    private boolean active;

    private Set<Role> roles;

    private String dateOfBirth;

    private Set<Address> addresses;

    private Map<Product, Integer> cart;

    private Set<Order> orders;



    public void setUsername(String username) {
        this.username = username;
        this.email = username;
    }
}
