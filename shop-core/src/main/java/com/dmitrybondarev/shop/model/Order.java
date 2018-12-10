package com.dmitrybondarev.shop.model;

import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Entity
@Table(name = "orderL")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    private User user;

    private String addressString;

    private Date dateOfOrder;

    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @Enumerated(EnumType.STRING)
    private TypeOfDelivery typeOfDelivery;

    @Enumerated(EnumType.STRING)
    private StatusOfPayment statusOfPayment;

    @Enumerated(EnumType.STRING)
    private StatusOfDelivery statusOfDelivery;

//    @ManyToMany
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orderL_products", joinColumns = @JoinColumn(name = "orderL_id"))
    private Map<Product, Integer> listOfProducts;
}

