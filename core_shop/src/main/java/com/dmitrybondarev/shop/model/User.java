package com.dmitrybondarev.shop.model;

import com.dmitrybondarev.shop.model.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String username;

    private String email;

    private String password;

    private boolean active;



    private String firstName;

    private String lastName;

    private String dateOfBirth;     //TODO find appropriate time class

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Address> addresses;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    private List<String> roles;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_cart", joinColumns = @JoinColumn(name = "user_id"))
    private Map<Product, Integer> cart;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    public boolean isAdmin() {
        if (roles.contains(Role.ADMIN)) {
            return true;
        }
        return false;
    }
}
