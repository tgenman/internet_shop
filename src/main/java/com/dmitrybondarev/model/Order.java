package com.dmitrybondarev.model;

import java.util.Map;

public class Order {

    private Client client;

    private Address address;

    private TypeOfPayment typeOfPayment;

    private TypeOfDelivery typeOfDelivery;

    private Map<String, Integer> listOfProducts;

    private StatusOfPayment statusOfPayment;

    private StatusOfDelivery statusOfDelivery;

    public Order(Client client,
                 Address address,
                 TypeOfPayment typeOfPayment,
                 TypeOfDelivery typeOfDelivery,
                 Map<String, Integer> listOfProducts,
                 StatusOfPayment statusOfPayment,
                 StatusOfDelivery statusOfDelivery) {
        this.client = client;
        this.address = address;
        this.typeOfPayment = typeOfPayment;
        this.typeOfDelivery = typeOfDelivery;
        this.listOfProducts = listOfProducts;
        this.statusOfPayment = statusOfPayment;
        this.statusOfDelivery = statusOfDelivery;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public TypeOfDelivery getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(TypeOfDelivery typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }

    public Map<String, Integer> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(Map<String, Integer> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public StatusOfPayment getStatusOfPayment() {
        return statusOfPayment;
    }

    public void setStatusOfPayment(StatusOfPayment statusOfPayment) {
        this.statusOfPayment = statusOfPayment;
    }

    public StatusOfDelivery getStatusOfDelivery() {
        return statusOfDelivery;
    }

    public void setStatusOfDelivery(StatusOfDelivery statusOfDelivery) {
        this.statusOfDelivery = statusOfDelivery;
    }
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
