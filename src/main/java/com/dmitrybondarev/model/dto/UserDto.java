package com.dmitrybondarev.model.dto;

import com.dmitrybondarev.model.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private String username;

    private String name;

    private String familyName;

    private String email;

    private String password;

    private boolean active;

    private Set<Role> roles;

    public void setUsername(String username) {
        this.username = username;
        this.email = username;
    }
}
