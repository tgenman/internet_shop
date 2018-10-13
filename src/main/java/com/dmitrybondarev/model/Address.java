package com.dmitrybondarev.model;
//TODO add Lombock lib
public class Address {

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String building;

    private String flat;

    public Address(String country, String city, int postalCode, String street, String building, String flat) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.building = building;
        this.flat = flat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
