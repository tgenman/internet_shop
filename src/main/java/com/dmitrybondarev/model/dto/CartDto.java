package com.dmitrybondarev.model.dto;

import com.dmitrybondarev.model.Product;
import lombok.Data;

import java.util.Map;

@Data
public class CartDto {

    private long id;

    private Map<Product, Integer> spec;
}
