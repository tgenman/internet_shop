package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.repository.api.ProductRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Log4j
@Repository
public class ProductRepoImp extends GenericRepoImpl<Product> implements ProductRepo {

    public ProductRepoImp() {
        setClazz(Product.class);
    }

    @Override
    public List<Product> findNonZeroQuantityProducts() {
        log.info("Finding findNonZeroQuantityProducts. Starting");
        List<Product> products = entityManager
                .createQuery("select product from Product product where product.quantity > 0", getClazz())
                .getResultList();
        log.info("Finding findNonZeroQuantityProducts. end");
        return products;
    }

}
