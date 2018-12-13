package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Category;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.repository.CategoryRepository;
import com.dmitrybondarev.shop.repository.ProductRepository;
import com.dmitrybondarev.shop.service.api.ProductService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.CategoryNotFoundException;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private MapperUtil mapperUtil;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public void addNewProductToStock(ProductDto productDto, MultipartFile file) throws ProductExistsException, IOException {
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

        Optional<Category> byCategoryNameContains = categoryRepository.findByCategoryNameContains(productDto.getCategoryString());
        product.setCategory(byCategoryNameContains.get());
        product.setId(null);
        productRepository.save(product);
    }

    @Override
    @Loggable
    @Transactional
    public Map<CategoryDto, List<ProductDto>> getAllExistProductDtosByFilter(String filter) {
        if (filter == null || filter.isEmpty()) {
            return this.convertListProductsToMapProductDtos(
                    productRepository.findAll());
        }

        Optional<Category> optionalCategory = categoryRepository.findByCategoryNameContains(filter);
        if (!optionalCategory.isPresent()) throw new CategoryNotFoundException("No category found with name: " + filter);
        Category category = optionalCategory.get();

        List<Product> products = productRepository.findAllByCategory(category);
        return this.convertListProductsToMapProductDtos(products);
    }

    @Override
    @Loggable
    @Transactional
    public Map<CategoryDto, List<ProductDto>> getProductDtosFromStockByFilter(String filter) {
        if (filter == null || filter.isEmpty()) {
            return this.convertListProductsToMapProductDtos(
                    productRepository.findAllByActiveTrueAndQuantityGreaterThan(0));
        }

        Optional<Category> optionalCategory = categoryRepository.findByCategoryNameContains(filter);
//        List<Category> listCategories = categoryRepository.findAllByCategoryNameContains(filter);
        if (!optionalCategory.isPresent()) throw new CategoryNotFoundException("No category found with name: " + filter);
        Category category = optionalCategory.get();

        List<Product> products = productRepository.findAllByActiveTrueAndCategoryAndQuantityGreaterThan(category, 0);
//        List<Product> products = productRepository.findAllByActiveTrueAndCategoryAndQuantityGreaterThan(listCategories, 0);
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
    public void editProductInStock(ProductDto productDto) {
        this.pullOutProductFromRepository(productDto.getId());
        Product product = mapperUtil.mapProductDtoToProduct(productDto);
        categoryRepository.save(product.getCategory());
        productRepository.save(product);
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
    private Map<CategoryDto, List<ProductDto>> convertListProductsToMapProductDtos(List<Product> products) {
        Map<CategoryDto, List<ProductDto>> map = new HashMap<>();

        for (Product product: products) {
            ProductDto productDto = mapperUtil.mapProductToProductDto(product);
            CategoryDto categoryDto = productDto.getCategory();

            if (map.containsKey(categoryDto)) {
                List<ProductDto> listProductDto = map.get(categoryDto);
                listProductDto.add(productDto);
            } else {
                List<ProductDto> listProductDto = new ArrayList<>();
                listProductDto.add(productDto);
                map.put(categoryDto, listProductDto);
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
