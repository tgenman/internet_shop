package com.dmitrybondarev.model;

import java.util.Map;

public class Product {

    private String title;

    private int price;

    private String category;

    private Map<String, String> parametrs;

    private int weight;

    private int volume;         //TODO decide about type of units of measure

    private int quantity;

    public Product(String title, int price, String category, Map<String, String> parametrs, int weight, int volume, int quantity) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.parametrs = parametrs;
        this.weight = weight;
        this.volume = volume;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, String> getParametrs() {
        return parametrs;
    }

    public void setParametrs(Map<String, String> parametrs) {
        this.parametrs = parametrs;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
