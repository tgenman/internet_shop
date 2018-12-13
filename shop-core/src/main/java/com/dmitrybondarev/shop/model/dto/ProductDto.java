package com.dmitrybondarev.shop.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
//@EqualsAndHashCode(of = "id")
public class ProductDto implements Serializable {

    private Long id;

    private boolean active;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String title;

    @NotNull
    @NotEmpty(message = "message.NotEmpty")
    private String brand;

    private CategoryDto category;

    private String categoryString;

    @NotNull
    @Min(value = 0)
    @Max(value = 2_000_000_000)
    private int price;

    private String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 2_000_000_000)
    private int weight;

    private String size;

    @NotNull
    @Min(value = 0)
    @Max(value = 2_000_000_000)
    private int dayOfWarranty;

    @NotNull
    @Min(value = 0)
    @Max(value = 2_000_000_000)
    private int quantity;

    private String filename;
}
