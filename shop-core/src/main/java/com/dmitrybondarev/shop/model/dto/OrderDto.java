package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class OrderDto implements Serializable {

    private Long id;

//    @NotNull
    private UserDto user;

    private int total;

//    @NotNull
    private String addressString;

//    @NotNull
    private Date dateOfOrder;

//    @NotNull
    private TypeOfPayment typeOfPayment;

//    @NotNull
    private TypeOfDelivery typeOfDelivery;

//    @NotNull
    private StatusOfPayment statusOfPayment;

//    @NotNull
    private StatusOfDelivery statusOfDelivery;

    private Map<ProductDto, Integer> listOfProducts;
}
