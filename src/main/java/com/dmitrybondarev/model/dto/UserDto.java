package com.dmitrybondarev.model.dto;

import com.dmitrybondarev.model.enums.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dmitrybondarev.util.validation.PasswordMatches;
import com.dmitrybondarev.util.validation.ValidEmail;
import lombok.Data;

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

    public void setUsername(String username) {
        this.username = username;
        this.email = username;
    }
}
