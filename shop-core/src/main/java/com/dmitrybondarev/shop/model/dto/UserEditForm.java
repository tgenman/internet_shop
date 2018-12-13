package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserEditForm {

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String firstName;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String lastName;

    private String dateOfBirth;
}
