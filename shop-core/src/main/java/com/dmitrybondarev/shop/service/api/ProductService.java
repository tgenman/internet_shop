package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductDto addNewProductToStock(ProductDto productDto, MultipartFile file) throws ProductExistsException, IOException;

    Map<String, List<ProductDto>> getAllExistProductsByFilter(String filter);

    Map<String, List<ProductDto>> getProductsFromStockByFilter(String filter);

    ProductDto getProductById(long productId);

    ProductDto editProductToStock(ProductDto productDto);

    void inactivateProduct(long productId);

    void activateProduct(long productId);

}
