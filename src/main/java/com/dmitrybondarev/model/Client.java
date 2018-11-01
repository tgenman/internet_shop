package com.dmitrybondarev.model;

import com.dmitrybondarev.model.enums.Role;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String familyName;

    private String email;

    private String password;        //TODO See logic to twidder

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name = "client_id"))
    private Set<Role> roles;

//    private String dateOfBirth;     //TODO find appropriate time class


//    private List<Address> addresses;
}
