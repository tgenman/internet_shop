package com.dmitrybondarev.shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(exclude = {"quantity", "active", "filename", "price"})
public class ProductDto implements Serializable {

    private Long id;

    private boolean active;

    private String title;

    private String brand;

    private String category;

    private int price;

    private String color;

    private int weight;

    private String size;

    private int dayOfWarranty;

    private int quantity;

    private String filename;
}
