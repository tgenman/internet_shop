package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoImp extends GenericRepoImpl<Product> implements ProductRepo {

    public ProductRepoImp() {
        setClazz(Product.class);
    }

    @Override
    @Loggable
    public List<Product> findNonZeroQuantityProducts() {
        return entityManager
                .createQuery("select product from Product product where product.quantity > 0", getClazz())
                .getResultList();
    }

    @Override
    public void delete(Product entity) {
        throw new UnsupportedOperationException("You cant delete Product from DB. Use inactivation");
    }

    @Override
    public void deleteById(Long entityId) {
        throw new UnsupportedOperationException("You cant delete Product from DB. Use inactivation");
    }
}
