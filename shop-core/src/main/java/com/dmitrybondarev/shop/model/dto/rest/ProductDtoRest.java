package com.dmitrybondarev.shop.model.dto.rest;

import lombok.Data;

import java.util.Map;

@Data
public class ProductDtoRest {

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

    private String filename;
}
