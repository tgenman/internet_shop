package com.dmitrybondarev.shop.model.dto.rest;

import lombok.Data;

import java.util.Map;

@Data
public class ProductDtoRest {

    private long id;

    private String title;

    private int price;

    private String category;

    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private String filename;

    private boolean active;
}
