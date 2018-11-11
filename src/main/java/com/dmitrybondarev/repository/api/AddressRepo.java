package com.dmitrybondarev.repository.api;

import com.dmitrybondarev.model.Address;

public interface AddressRepo {
    Address saveAddress(Address address);

    Address findById(long id);

    void updateAddress(Address address);

    boolean removeById(long id);
}
