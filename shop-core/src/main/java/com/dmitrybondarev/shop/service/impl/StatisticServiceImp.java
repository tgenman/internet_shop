package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.repository.OrderRepository;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.service.api.StatisticService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImp implements StatisticService{

    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private MapperUtil mapperUtil;

    public StatisticServiceImp(OrderRepository orderRepository, MapperUtil mapperUtil) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public List<ProductDto> getTopFiveProductsDTOByCashFlow() {
        Iterable<Order> allOrders = orderRepository.findAll();
        Map<Product, Integer> allProducts = new HashMap<>();

        for (Order order : allOrders) {
            Map<Product, Integer> orderProducts = order.getListOfProducts();
            for (Product product : order.getListOfProducts().keySet()) {
                if(!allProducts.containsKey(product)) allProducts.put(new Product(), 0);
                allProducts.put(product,
                        allProducts.get(product) + orderProducts.get(product) * product.getPrice());
            }
        }

        List<Map.Entry<Product, Integer>> collect = allProducts.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        int size = collect.size() < 6 ? collect.size() : 5;

        return collect.stream()
                .limit(size)
                .map(x -> mapperUtil.mapProductToProductDto(x.getKey()))
                .collect(Collectors.toList());

//        List<ProductDto> result = new ArrayList<>();
//        for (int i = 0; i < size; i++)
//            result.add(
//                    this.mapProductToProductDto(
//                            collect.get(i).getKey()));
//        return result;
    }



    @Override
    @Loggable
    @Transactional
    public List<ProductDtoRest> getTopFiveProductsDTORestByCashFlow() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(x -> mapperUtil.mapProductDtoToProductDtoRest(
                        mapperUtil.mapProductToProductDto(x)))
                .collect(Collectors.toList());
    }



}
