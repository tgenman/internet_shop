package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class CartDto implements Serializable {

    private Long id;

    private Map<ProductDto, Integer> contentDto;

    private String sessionId;
}
