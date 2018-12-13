package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.web.validation.annotation.PasswordMatches;
import com.dmitrybondarev.shop.web.validation.annotation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@PasswordMatches
public class UserDto implements Serializable {

    private Long id;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String password;
    private String matchingPassword;

    private boolean enabled;

    private List<String> roles;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String firstName;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String lastName;

    private String dateOfBirth;

    private Set<AddressDto> addresses;

    private CartDto cartDto;

}
