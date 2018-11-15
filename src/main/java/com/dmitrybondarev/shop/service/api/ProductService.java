package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductDto addNewProductToStock(ProductDto productDto);

    Map<String, List<ProductDto>> getAllProducts();

    Map<String, List<ProductDto>> getProductsFromStock();

    ProductDto getProductById(long id);

    ProductDto editProductToStock(ProductDto productDto);

    void removeProductFromStock(long id);
}
