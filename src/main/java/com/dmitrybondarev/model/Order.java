package com.dmitrybondarev.model;

import com.dmitrybondarev.model.enums.StatusOfDelivery;
import com.dmitrybondarev.model.enums.StatusOfPayment;
import com.dmitrybondarev.model.enums.TypeOfDelivery;
import com.dmitrybondarev.model.enums.TypeOfPayment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Map;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

