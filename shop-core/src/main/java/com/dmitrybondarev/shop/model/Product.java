package com.dmitrybondarev.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
//@EqualsAndHashCode(of = "id")
@Entity(name = "Product")
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
