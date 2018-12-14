package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Category;
import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    void addNewProductToStock(ProductDto productDto, MultipartFile file) throws ProductExistsException, IOException;

    Map<CategoryDto, List<ProductDto>> getAllExistProductDtosByFilter(String filter);

    Map<CategoryDto, List<ProductDto>> getProductDtosFromStockByFilter(String filter);

    ProductDto getProductById(long productId);

    void editProductInStock(ProductDto productDto, MultipartFile file) throws ProductExistsException, IOException;

    void inactivateProduct(long productId);

    void activateProduct(long productId);

    List<ProductDtoRest> getListAllAdvertisingProductDtoRest();
}
