package com.dmitrybondarev.shop.model;

import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "Order")
@Table(name = "order_entity")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private int total;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_entity_product",
            joinColumns = @JoinColumn(name = "order_entity_id"))
    private Map<Product, Integer> listOfProducts;

    private String bill;

    public void setListOfProducts(Map<Product, Integer> i) {
        this.listOfProducts = i;
    }

    public Map<Product, Integer> getListOfProducts() {
        return  new HashMap<>();
    }



}

