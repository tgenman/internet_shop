package com.dmitrybondarev.shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(exclude = {"quantity", "active", "filename", "price"})
public class ProductDto implements Serializable {

    private Long id;

    private String title;

    private int price;

    private String category;

    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private int quantity;

    private boolean active;

    private String filename;
}
