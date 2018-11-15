package com.dmitrybondarev.shop.repository.api;

import com.dmitrybondarev.shop.model.Product;

import java.util.List;

public interface ProductRepo extends GenericRepo<Product>{

    List<Product> findNonZeroQuantityProducts();
}
