package com.dmitrybondarev.shop.repository;

import com.dmitrybondarev.shop.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
