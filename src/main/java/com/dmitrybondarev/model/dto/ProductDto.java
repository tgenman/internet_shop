package com.dmitrybondarev.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProductDto {

    private long id;

    private String title;

    private int price;

    private String category;

    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private int quantity;

    public boolean isImage() {
        return false;
    }
}
