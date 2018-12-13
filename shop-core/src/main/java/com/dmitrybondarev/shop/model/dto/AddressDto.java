package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AddressDto implements Serializable {

    private Long id;

    private boolean active;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String country;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String city;

    private int postalCode;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String street;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String building;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String flat;

    @Override
    public String toString() {
        return postalCode + " " + country + " " + city + " " + street + " " + building + " " + flat;
    }


}
