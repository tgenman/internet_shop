package com.dmitrybondarev.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Order_list")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne
    private Client client;

    @OneToOne
    private Address address;

//    private TypeOfPayment typeOfPayment;
//
//    private TypeOfDelivery typeOfDelivery;
//
//    private Map<Product, Integer> listOfProducts;
//
//    private StatusOfPayment statusOfPayment;
//
//    private StatusOfDelivery statusOfDelivery;
}

