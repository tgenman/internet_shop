package com.dmitrybondarev.shop.repository.impl;


import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.repository.api.AddressRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class AddressRepoImp extends GenericRepoImpl<Address> implements AddressRepo {

    public AddressRepoImp() {
        setClazz(Address.class);
    }
}
