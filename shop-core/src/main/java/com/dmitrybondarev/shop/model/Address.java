package com.dmitrybondarev.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "Address")
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;

    @Override
    public String toString() {
        return postalCode + " " + country + " " + city + " " + street + " " + building + " " + flat;
    }
}
