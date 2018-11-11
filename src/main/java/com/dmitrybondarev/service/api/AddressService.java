package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.dto.AddressDto;

public interface AddressService {

    Address addNewAddress(AddressDto addressDto, long userId);

    AddressDto getAddressDtoById(long id);

    Address editAddress(AddressDto addressDto);

    void deleteAddress(long addressId, long userId);
}
