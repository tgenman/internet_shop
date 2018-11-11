package com.dmitrybondarev.repository.impl;


import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.repository.api.AddressRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j
@Repository
public class AddressRepoImp implements AddressRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Address saveAddress(Address address) {
        log.info("Saving start");
        entityManager.persist(address);
        log.info("Saving end. Successful");
        return address;
    }

    @Override
    public Address findById(long id) {
        log.info("Finding Address by id = " + id + " start.");
        Address address = entityManager.find(Address.class, id);
        if (address == null) {
            log.warn("Finding end. Address doesnt find");
        } else {
            log.info("Finding end. Address = " + address.toString());
        }
        return address;
    }

    @Override
    public void updateAddress(Address address) {
        log.info("updateAddress start");
        log.info("input: " + address.toString());
        Address merge = entityManager.merge(address);
        log.info("output: " + merge.toString());
        log.info("updateAddress end");
    }

    @Override
    public boolean removeById(long id) {
        Address address = entityManager.find(Address.class, id);
        if (address != null) {
            entityManager.remove(address);
            return true;
        }
        return false;
    }
}
