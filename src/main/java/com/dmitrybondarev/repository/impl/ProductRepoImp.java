package com.dmitrybondarev.repository.impl;

import com.dmitrybondarev.model.Product;
import com.dmitrybondarev.repository.api.ProductRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Log4j
public class ProductRepoImp implements ProductRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Product save(Product product) {
        log.info("Saving product start.");
        entityManager.persist(product);
        log.info("Saving end. Successful");
        return product;
    }

    @Override
    public Product findById(long id) {
        log.info("Finding Product by id = " + id + " start.");
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            log.warn("Finding end. Product doesnt find");
        } else {
            log.info("Finding end. Product = " + product.getTitle());
        }
        return product;
    }

    @Override
    public Product findByWord(String word) {
        throw new UnsupportedOperationException();
    }

    public Product findByTitle(String title) {
        log.info("Finding Product by Title = " + title + " start.");
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.title = :title", Product.class);
        query.setParameter("title", title);
        Product singleResult;
        try {
            singleResult = query.getSingleResult();
            log.info("Finding end. User found");
        } catch (NoResultException e) {
            log.warn("Finding end. User doesnt find");
            return null;
        }
        return singleResult;
    }

    @Override
    public List<Product> findAll() {
        log.info("Finding all. Starting");
        List<Product> products = entityManager.createQuery("select product from Product product", Product.class).getResultList();
        log.info("Finding all. end");
        return products;
    }

    @Override
    public List<Product> findNonZeroQuantityProducts() {
        log.info("Finding findNonZeroQuantityProducts. Starting");
        List<Product> products = entityManager.createQuery("select product from Product product where product.quantity > 0", Product.class).getResultList();
        log.info("Finding findNonZeroQuantityProducts. end");
        return products;
    }

    @Override
    public boolean removeById(long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public void updateProduct(Product product) {
        Product merge = entityManager.merge(product);
    }
}
