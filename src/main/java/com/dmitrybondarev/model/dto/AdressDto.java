package com.dmitrybondarev.model.dto;

import lombok.Data;

@Data
public class AdressDto {

    private long id;

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;
}
