package com.dmitrybondarev.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(exclude = {"quantity", "parameters"})       //TODO find how to hashcode Map
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int price;

    private String category;

//    private Map<String, String> parameters;

    private int weight;

    private int volume;         //TODO decide about type of units of measure

    private int quantity;
}
