package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.dto.AddressDto;
import org.springframework.transaction.annotation.Transactional;

public interface AddressService {
    @Transactional
    Address addNewAddress(AddressDto addressDto, long userId);

    @Transactional
    Address getAddressDtoById(long id);

    @Transactional
    Address editAddress(AddressDto addressDto);

    @Transactional
    void deleteAddress(long addressId, long userId);
}
