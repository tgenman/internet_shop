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
@EqualsAndHashCode(of = "id")
@Entity(name = "Category")
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;
}
