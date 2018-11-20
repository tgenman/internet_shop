package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.dto.AddressDto;

public interface AddressService {

    Address addNewAddress(AddressDto addressDto, long userId);

    AddressDto getAddressDtoById(long id);

    Address editAddress(AddressDto addressDto);

    void deleteAddress(long addressId, long userId);
}
