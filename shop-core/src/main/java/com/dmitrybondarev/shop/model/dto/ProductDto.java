package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ProductDto implements Serializable {

    private long id;

    private String title;

    private int price;

    private String category;

    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private int quantity;
}
