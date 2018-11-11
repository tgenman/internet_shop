package com.dmitrybondarev.model.dto;

import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.enums.StatusOfDelivery;
import com.dmitrybondarev.model.enums.StatusOfPayment;
import com.dmitrybondarev.model.enums.TypeOfDelivery;
import com.dmitrybondarev.model.enums.TypeOfPayment;
import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {

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
