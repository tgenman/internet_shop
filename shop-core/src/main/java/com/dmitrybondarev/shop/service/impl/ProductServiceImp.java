package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    private DozerBeanMapper mapper;

    public ProductServiceImp(ProductRepository productRepository, DozerBeanMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto addNewProductToStock(ProductDto productDto) {
        Product product = this.mapProductDtoToProduct(productDto);
        product.setId(null);
        productRepository.save(product);
        return productDto;
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getProductsFromStock() {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(x -> x.getQuantity() > 0)
                .collect(Collectors.toList());
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto getProductById(long id) {
        Product byId = productRepository.findById(id).get();
        return this.mapProductToProductDto(byId);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto editProductToStock(ProductDto productDto) {
        Product product = this.mapProductDtoToProduct(productDto);
        productRepository.save(product);
        return productDto;
    }

    @Override
    @Loggable
    @Transactional
    public void removeProductFromStock(long id) {  //TODO make unusable
        productRepository.deleteById(id);
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
