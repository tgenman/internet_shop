package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import com.dmitrybondarev.shop.service.api.ProductService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public ProductDto addNewProductToStock(ProductDto productDto) {
        log.info("Adding new Product. ProductDto title=" + productDto.getTitle());

        Product product = this.mapProductDtoToProduct(productDto);
        productRepo.save(product);
        return productDto;

    }

    @Override
    @Transactional
    public Map<String, List<ProductDto>> getAllProducts() {
        log.info("Get All Products. Start");

        List<Product> products = productRepo.findAll();

        Map<String, List<ProductDto>> productDtos = this.convertListProductsToMapProductDtos(products);

        log.info("Get All Products. end");
        return productDtos;
    }

    @Override
    @Transactional
    public Map<String, List<ProductDto>> getProductsFromStock() {
        log.info("Get Products from stock. Start");

        List<Product> products = productRepo.findNonZeroQuantityProducts();

        Map<String, List<ProductDto>> productDtos = this.convertListProductsToMapProductDtos(products);

        log.info("Get Products from stock. End");
        return productDtos;
    }

    @Override
    @Transactional
    public ProductDto getProductById(long id) {
        Product byId = productRepo.findById(id);
        return this.mapProductToProductDto(byId);
    }

    @Override
    @Transactional
    public ProductDto editProductToStock(ProductDto productDto) {
        Product product = this.mapProductDtoToProduct(productDto);
        productRepo.update(product);
        return productDto;
    }

    @Override
    @Transactional
    public void removeProductFromStock(long id) {
        log.info("removeProductFromStock start");
        productRepo.deleteById(id);
        log.info("removeProductFromStock end");
    }


    // ============== NON-API ============

    private ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        mapper.map(product, productDto);
        return productDto;
    }

    private Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        mapper.map(productDto, product);
        return product;
    }

    private Map<String, List<ProductDto>> convertListProductsToMapProductDtos(List<Product> products) {
        Map<String, List<ProductDto>> map = new HashMap<>();

        for (Product product: products) {
            ProductDto productDto = this.mapProductToProductDto(product);
            String category = productDto.getCategory();
            if (map.containsKey(category)) {
                List<ProductDto> listProductDto = map.get(category);
                listProductDto.add(productDto);
            } else {
                List<ProductDto> listProductDto = new ArrayList<>();
                listProductDto.add(productDto);
                map.put(category, listProductDto);
            }
        }
        return map;
    }
}
