package com.dmitrybondarev.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Order {

    private Client client;

    private Address address;

    private TypeOfPayment typeOfPayment;

    private TypeOfDelivery typeOfDelivery;

    private Map<Product, Integer> listOfProducts;

    private StatusOfPayment statusOfPayment;

    private StatusOfDelivery statusOfDelivery;
}

enum TypeOfPayment {
    CASH, NONCASH;
}

enum TypeOfDelivery {
    POST, TRANSPORT_COMPANY, POSTANAT, QUADCOPTER;
}

enum StatusOfPayment {
    WAITING_FOR_PAYMENT, PAID;
}

enum StatusOfDelivery {
    WAITING_FOR_PAYMENT, WAITING_FOR_SHIPMENT, SHIPPED, DELIVERED;
}
