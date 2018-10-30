package com.dmitrybondarev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String familyName;

//    private String dateOfBirth;     //TODO find appropriate time class

    private String email;

    private String password;        //TODO See logic to twidder

//    private List<Address> addresses;
}
