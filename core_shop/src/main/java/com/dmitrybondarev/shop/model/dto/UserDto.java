package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.enums.Role;
import com.dmitrybondarev.shop.util.validation.PasswordMatches;
import com.dmitrybondarev.shop.util.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@PasswordMatches
public class UserDto implements Serializable {

    private long id;

    private String username;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String firstName;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String lastName;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String password;
    private String matchingPassword;

    private boolean active;

//    private Set<Role> roles;

    private List<String> roles;


    private String dateOfBirth;

    private Set<Address> addresses;

    private Map<Product, Integer> cart;

    private Set<Order> orders;
}
