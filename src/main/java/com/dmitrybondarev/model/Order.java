package com.dmitrybondarev.model;

import com.dmitrybondarev.model.enums.StatusOfDelivery;
import com.dmitrybondarev.model.enums.StatusOfPayment;
import com.dmitrybondarev.model.enums.TypeOfDelivery;
import com.dmitrybondarev.model.enums.TypeOfPayment;
import lombok.Data;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

@Data
@Entity
@Table(name = "orderL")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private User user;

    private String address;

    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @Enumerated(EnumType.STRING)
    private TypeOfDelivery typeOfDelivery;

    @Enumerated(EnumType.STRING)
    private StatusOfPayment statusOfPayment;

    @Enumerated(EnumType.STRING)
    private StatusOfDelivery statusOfDelivery;

//    @ManyToMany
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orderL_products", joinColumns = @JoinColumn(name = "orderL_id"))
    private Map<Product, Integer> listOfProducts;
}

