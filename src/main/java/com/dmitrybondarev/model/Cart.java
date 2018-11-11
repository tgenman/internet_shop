package com.dmitrybondarev.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Product, Integer> spec;
}
