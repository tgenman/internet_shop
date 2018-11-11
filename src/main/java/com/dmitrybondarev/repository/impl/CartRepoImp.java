package com.dmitrybondarev.repository.impl;

import com.dmitrybondarev.repository.api.CartRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j
@Repository
public class CartRepoImp implements CartRepo {

    @PersistenceContext
    private EntityManager entityManager;

}
