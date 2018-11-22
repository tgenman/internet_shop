package com.dmitrybondarev.shop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

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
