package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.Product;
import lombok.Data;

import java.util.Map;

@Data
public class CartDto {

    private Long id;

    private Map<Product, Integer> content;

    private String sessionId;
}
