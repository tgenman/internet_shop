package com.dmitrybondarev.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;
}
