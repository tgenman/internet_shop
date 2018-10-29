package com.dmitrybondarev.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {

    private int id;

    private String name;

    private String familyName;

    private String dateOfBirth;     //TODO find appropriate time class

    private String email;

    private String password;        //TODO See logic to twidder

    private List<Address> addresses;
}
