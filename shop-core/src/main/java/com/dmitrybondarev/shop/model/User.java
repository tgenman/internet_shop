package com.dmitrybondarev.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @Column(length = 60)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<String> roles;


    private String firstName;

    private String lastName;

    private String dateOfBirth;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private Cart cart;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Order> orderList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public boolean isAdmin() {
        return roles.contains("ROLE_ADMIN");
    }
}
