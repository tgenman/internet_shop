package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import com.dmitrybondarev.shop.service.api.ProductService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Loggable
    @Transactional
    public ProductDto addNewProductToStock(ProductDto productDto) {
        Product product = this.mapProductDtoToProduct(productDto);
        product.setId(null);
        productRepo.save(product);
        return productDto;
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getProductsFromStock() {
        List<Product> products = productRepo.findNonZeroQuantityProducts();
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto getProductById(long id) {
        Product byId = productRepo.findById(id);
        return this.mapProductToProductDto(byId);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto editProductToStock(ProductDto productDto) {
        Product product = this.mapProductDtoToProduct(productDto);
        productRepo.update(product);
        return productDto;
    }

    @Override
    @Loggable
    @Transactional
    public void removeProductFromStock(long id) {
        productRepo.deleteById(id);
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
