package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.Address;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepo {
    @Transactional
    Address saveAddress(Address address);

    @Transactional
    Address findById(long id);

    void updateAddress(Address address);

    boolean removeById(long id);
}
