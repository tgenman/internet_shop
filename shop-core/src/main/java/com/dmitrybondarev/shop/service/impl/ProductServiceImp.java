package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.ProductExistsException;
import com.dmitrybondarev.shop.util.exception.ProductNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    private MapperUtil mapperUtil;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductServiceImp(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto addNewProductToStock(ProductDto productDto, MultipartFile file) throws ProductExistsException, IOException {
        Optional<Product> optionalProduct = productRepository.findByTitleAndBrand(productDto.getTitle(), productDto.getBrand());
        if (optionalProduct.isPresent())
            throw new ProductExistsException("There is a product with that title: " + productDto.getTitle() + " and brand: " + productDto.getBrand());

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            productDto.setFilename(resultFilename);
        }

        Product product = mapperUtil.mapProductDtoToProduct(productDto);
        product.setId(null);
        Product save = productRepository.save(product);

        return save != null ? mapperUtil.mapProductToProductDto(save) : null;
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getAllExistProductsByFilter(String filter) {
        List<Product> products;

        if (filter != null && !filter.isEmpty()) {
            products = productRepository.findAllByCategory(filter);
        } else {
            products = productRepository.findAll();
        }

        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public Map<String, List<ProductDto>> getProductsFromStockByFilter(String filter) {
        List<Product> products;

        if (filter != null && !filter.isEmpty()) {
            products = productRepository.findAllByActiveTrueAndCategoryAndQuantityGreaterThan(filter, 0);
        } else {
            products = productRepository.findAll();
        }
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto getProductById(long productId) {
        Product product = this.pullOutProductFromRepository(productId);
        return mapperUtil.mapProductToProductDto(product);
    }

    @Override
    @Loggable
    @Transactional
    public ProductDto editProductToStock(ProductDto productDto) {
        this.pullOutProductFromRepository(productDto.getId());
        Product product = mapperUtil.mapProductDtoToProduct(productDto);
        productRepository.save(product);
        return productDto;
    }

    @Override
    @Loggable
    @Transactional
    public void inactivateProduct(long productId) {
        Product product = this.pullOutProductFromRepository(productId);
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    @Loggable
    @Transactional
    public void activateProduct(long productId) {
        Product product = this.pullOutProductFromRepository(productId);
        product.setActive(true);
        productRepository.save(product);
    }

    // ============== NON-API ============

    @Loggable
    private Map<String, List<ProductDto>> convertListProductsToMapProductDtos(List<Product> products) {
        Map<String, List<ProductDto>> map = new HashMap<>();

        for (Product product: products) {
            ProductDto productDto = mapperUtil.mapProductToProductDto(product);
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

    @Loggable
    private Product pullOutProductFromRepository(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) throw new ProductNotFoundException("No product found with id: "+ productId);
        return optionalProduct.get();
    }
}
