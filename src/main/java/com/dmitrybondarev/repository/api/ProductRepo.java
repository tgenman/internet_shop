package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.Product;

import java.util.List;

public interface ProductRepo {

    Product save(Product product);

    Product findById(long id);

    Product findByWord(String word);

    Product findByTitle(String title);

    List<Product> findAll();

    List<Product> findNonZeroQuantityProducts();

    boolean removeById(long id);

    void updateProduct(Product product);
}
