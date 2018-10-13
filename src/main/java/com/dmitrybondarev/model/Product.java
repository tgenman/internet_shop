package com.dmitrybondarev.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"quantity", "parameters"})       //TODO find how to hashcode Map
public class Product {

    private String title;

    private int price;

    private String category;

    private Map<String, String> parameters;

    private int weight;

    private int volume;         //TODO decide about type of units of measure

    private int quantity;
}
