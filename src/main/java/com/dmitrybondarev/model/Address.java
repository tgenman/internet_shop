package com.dmitrybondarev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;

    @Override
    public String toString() {
        return postalCode + " " + country + " " + city + " " + street + " " + building + " " + flat;
    }
}
