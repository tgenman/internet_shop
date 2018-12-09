package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.repository.AddressRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImp implements AddressService {

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    private MapperUtil mapperUtil;

    public AddressServiceImp(UserRepository userRepository, AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Loggable
    @Transactional
    public Address addNewAddress(AddressDto addressDto, long userId) {
        Address address = mapperUtil.mapAddressDtoToAddress(addressDto);
        address.setId(null);
        User user = userRepository.findById(userId).get();
        user.getAddresses().add(address);
        userRepository.save(user);
        addressRepository.save(address);
        return address;
    }

    @Override
    @Loggable
    @Transactional
    public AddressDto getAddressDtoById(long id) {
        Address address = addressRepository.findById(id).get();
        return mapperUtil.mapAddressToAddressDto(address);
    }

    @Override
    @Loggable
    @Transactional
    public Address editAddress(AddressDto addressDto) {
        Address address = mapperUtil.mapAddressDtoToAddress(addressDto);
        addressRepository.save(address);
        return address;
    }

    @Override
    @Loggable
    @Transactional
    public void deleteAddress(long addressId, long userId) {

        Address address = addressRepository.findById(addressId).get();

        User user = userRepository.findById(userId).get();
        user.getAddresses().remove(address);

        userRepository.save(user);
        addressRepository.deleteById(addressId);
    }
}
