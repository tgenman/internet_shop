package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.util.validation.annotation.ValidPassword;
import lombok.Data;

@Data
public class PasswordDto {

    private String oldPassword;

    @ValidPassword
    private String newPassword;
}
