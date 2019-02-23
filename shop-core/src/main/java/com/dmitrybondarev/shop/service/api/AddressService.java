package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.util.exception.AddressNotFoundException;

import java.util.Set;

public interface AddressService {

    void addNewAddress(AddressDto addressDto, String userEmail);

    AddressDto getAddressDtoById(long id) throws AddressNotFoundException;

    Address editAddress(AddressDto addressDto);

    public Set<Address> getAllActiveAddressByUserEmail(String userEmail);

    void inactivateAddress(long userId) throws AddressNotFoundException;
}
