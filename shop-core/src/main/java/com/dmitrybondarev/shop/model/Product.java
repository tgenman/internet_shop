package com.dmitrybondarev.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

@Data
@EqualsAndHashCode(exclude = {"quantity", "active", "filename", "price"})
@Entity(name = "Product")
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String category;

    private int price;

    @CollectionTable(
            name = "product_parameter",
            joinColumns = @JoinColumn(name = "product_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> parameters;

    private int weight;

    private int volume;

    private int quantity;

    private boolean active;

    private String filename;

}
