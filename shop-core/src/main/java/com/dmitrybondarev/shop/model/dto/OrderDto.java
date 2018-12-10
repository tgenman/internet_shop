package com.dmitrybondarev.shop.model.dto;

import com.dmitrybondarev.shop.model.enums.StatusOfDelivery;
import com.dmitrybondarev.shop.model.enums.StatusOfPayment;
import com.dmitrybondarev.shop.model.enums.TypeOfDelivery;
import com.dmitrybondarev.shop.model.enums.TypeOfPayment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class OrderDto implements Serializable {

    private Long id;

    private UserDto userDto;

    private String addressString;

    private Date dateOfOrder;

    private TypeOfPayment typeOfPayment;

    private TypeOfDelivery typeOfDelivery;

    private StatusOfPayment statusOfPayment;

    private StatusOfDelivery statusOfDelivery;

    private Map<ProductDto, Integer> listOfProductDtos;
}
