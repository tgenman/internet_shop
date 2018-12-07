package com.dmitrubondarev.stand.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private Long id;

    private String title;

    private String category;

    private int price;

//    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private int quantity;
}
