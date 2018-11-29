package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class OrderDto implements Serializable {

    private long id;

    private User user;

    private String address;

    private String dateOfOrder;

    private TypeOfPayment typeOfPayment;

    private TypeOfDelivery typeOfDelivery;

    private StatusOfPayment statusOfPayment;

    private StatusOfDelivery statusOfDelivery;

    private Map<Product, Integer> listOfProducts;
}
