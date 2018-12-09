package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {

    private Long id;

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;


}
