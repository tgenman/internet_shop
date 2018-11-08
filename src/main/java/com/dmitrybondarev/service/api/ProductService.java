package com.dmitrybondarev.service.api;

import com.dmitrybondarev.exception.TitleExistException;
import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.model.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product addNewProductToStock(ProductDto productDto) throws TitleExistException;

    Map<String, List<ProductDto>> getAllProducts();

    Map<String, List<ProductDto>> getProductsFromStock();
}
