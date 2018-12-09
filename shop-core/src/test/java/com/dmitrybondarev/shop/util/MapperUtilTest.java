package com.dmitrybondarev.shop.util;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapperUtilTest {

    private MapperUtil mapperUtil;

    public MapperUtilTest() {
        this.mapperUtil = new MapperUtil(new DozerBeanMapper());
    }

    @Test
    public void testMapProductDtoToProduct() {
//      Values
        String title = "1";
        String category = "1";
        int price = 1;
        int quantity = 1;
        int volume = 1;
        int weight = 1;
        String filename = "1";
        boolean active = true;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "1");
        parameters.put("insurance", "1");

//      Input
        ProductDto input = new ProductDto();
        input.setTitle(title);
        input.setCategory(category);
        input.setPrice(price);
        input.setQuantity(quantity);
        input.setVolume(volume);
        input.setWeight(weight);
        input.setParameters(parameters);
        input.setFilename(filename);
        input.setActive(active);

//      Run
        Product output = mapperUtil.mapProductDtoToProduct(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(title, output.getTitle());
        Assert.assertEquals(category, output.getCategory());
        Assert.assertEquals(price, output.getPrice());
        Assert.assertEquals(quantity, output.getQuantity());
        Assert.assertEquals(volume, output.getVolume());
        Assert.assertEquals(weight, output.getWeight());
        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
        Assert.assertEquals(filename, output.getFilename());
        Assert.assertEquals(active, output.isActive());
    }

    @Test
    public void testMapProductToProductDto() {
//      Values
        String title = "1";
        String category = "1";
        int price = 1;
        int quantity = 1;
        int volume = 1;
        int weight = 1;
        String filename = "1";
        boolean active = true;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "1");
        parameters.put("insurance", "1");

//      Input
        Product input = new Product();
        input.setTitle(title);
        input.setCategory(category);
        input.setPrice(price);
        input.setQuantity(quantity);
        input.setVolume(volume);
        input.setWeight(weight);
        input.setParameters(parameters);
        input.setFilename(filename);
        input.setActive(active);

//      Run
        ProductDto output = mapperUtil.mapProductToProductDto(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(title, output.getTitle());
        Assert.assertEquals(category, output.getCategory());
        Assert.assertEquals(price, output.getPrice());
        Assert.assertEquals(quantity, output.getQuantity());
        Assert.assertEquals(volume, output.getVolume());
        Assert.assertEquals(weight, output.getWeight());
        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
        Assert.assertEquals(filename, output.getFilename());
        Assert.assertEquals(active, output.isActive());
    }


}