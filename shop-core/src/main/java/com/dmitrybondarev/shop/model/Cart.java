package com.dmitrybondarev.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "Cart")
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String sessionId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"))
    private Map<Product, Integer> products;


}
