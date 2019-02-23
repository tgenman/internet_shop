package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.repository.AddressRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.util.MapperUtil;
import com.dmitrybondarev.shop.util.exception.AddressNotFoundException;
import com.dmitrybondarev.shop.util.exception.UserNotFoundException;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

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
    public void addNewAddress(AddressDto addressDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("Dont find user with email: " + userEmail));

        Address address = mapperUtil.mapAddressDtoToAddress(addressDto);
        address.setId(null);

        user.getAddresses().add(address);
        userRepository.save(user);

        addressRepository.save(address);
    }

    @Override
    @Loggable
    @Transactional
    public AddressDto getAddressDtoById(long addressId) throws AddressNotFoundException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address doesn't found  with id: " + addressId));
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
    public void inactivateAddress(long addressId) throws AddressNotFoundException {
        Address address =  addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address doesn't found  with id: " + addressId));

        address.setActive(false);
        addressRepository.save(address);
    }

    @Override
    @Loggable
    @Transactional
    public Set<Address> getAllActiveAddressByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("Dont find all users with email: " + userEmail));

        Set<Address> addresses = user.getAddresses();
        return addresses.stream()
                .filter(Address::isActive)
                .collect(Collectors.toSet());
    }
}
