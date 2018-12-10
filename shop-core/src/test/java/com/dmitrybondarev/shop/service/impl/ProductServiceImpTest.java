package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.util.MapperUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImpTest {

    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private MapperUtil mapperUtil;

    @MockBean
    private ProductRepository productRepositoryMock;



//    @Test
//    public void addNewProductToStock() {
//
////      Values
//        String title = "1";
//        String category = "1";
//        int price = 1;
//        int quantity = 1;
//        int size = 1;
//        int weight = 1;
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("color", "1");
//        parameters.put("insurance", "1");
//
////      Input
//        ProductDto input = new ProductDto();
//        input.setTitle(title);
//        input.setCategory(category);
//        input.setPrice(price);
//        input.setQuantity(quantity);
//        input.setSize(size);
//        input.setWeight(weight);
//        input.setParameters(parameters);
//
////      Mocking
//        when(productRepositoryMock
//                .save(mapperUtil.mapProductDtoToProduct(input)))
//                .thenReturn(mapperUtil.mapProductDtoToProduct(input));
//
////      Run
//        ProductDto output = productServiceImp.addNewProductToStock(input);
//
////      Assert
//        Assert.assertNotNull(output);
//        Assert.assertEquals(title, output.getTitle());
//        Assert.assertEquals(category, output.getCategory());
//        Assert.assertEquals(price, output.getPrice());
//        Assert.assertEquals(quantity, output.getQuantity());
//        Assert.assertEquals(size, output.getSize());
//        Assert.assertEquals(weight, output.getWeight());
//        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
//        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
//
////        Product product = this.mapProductDtoToProduct(input);
////        product.setId(null);
////        Mockito.verify(productRepository, Mockito.times(1))
////                .save(product);
//
//    }


}