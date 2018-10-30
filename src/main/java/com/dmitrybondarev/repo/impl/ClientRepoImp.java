package com.dmitrybondarev.repo.impl;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.repo.api.ClientRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ClientRepoImp implements ClientRepo {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addClient(Client client) {
        entityManager.persist(client);
    }


}
