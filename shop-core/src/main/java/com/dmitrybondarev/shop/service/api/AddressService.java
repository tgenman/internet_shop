package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.dto.AddressDto;

import java.util.List;
import java.util.Set;

public interface AddressService {

    void addNewAddress(AddressDto addressDto, String userEmail);

    AddressDto getAddressDtoById(long id);

    Address editAddress(AddressDto addressDto);

    public Set<Address> getAllActiveAddressByUserEmail(String userEmail);

    void inactivateAddress(long userId);
}
