package com.dmitrybondarev.model;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

@Entity
@Data
//@EqualsAndHashCode(exclude = {"quantity", "parameters"})       //TODO find how to hashcode Map
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int price;

    private String category;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_parameter", joinColumns = @JoinColumn(name = "product_id"))
    private Map<String, String> parameters;

    private int weight;

    private int volume;         //TODO decide about type of units of measure

    private int quantity;
}
