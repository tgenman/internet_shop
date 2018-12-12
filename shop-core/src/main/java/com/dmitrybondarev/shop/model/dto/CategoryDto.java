package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    private Long id;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String categoryName;

    @Override
    public String toString() {
        return categoryName;
    }
}
