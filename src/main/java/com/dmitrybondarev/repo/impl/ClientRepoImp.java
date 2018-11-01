package com.dmitrybondarev.repo.impl;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.repo.api.ClientRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ClientRepoImp implements ClientRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addClient(Client client) {
        entityManager.persist(client);
    }

    @Transactional(readOnly=true)
    public List<Client> getAllClients() {
        return entityManager.createQuery("select client from Client client").getResultList();
    }

    @Transactional(readOnly=true)
    public boolean findByEmail(String email) {

//        boolean result = entityManager.createQuery("select client from Client client where client = email")
//                .getResultList().isEmpty();
//
//
//        if (result) return true;
        return false;

//        return true;
    }

}
