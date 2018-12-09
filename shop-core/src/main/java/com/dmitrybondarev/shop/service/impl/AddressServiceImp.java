package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.repository.AddressRepository;
import com.dmitrybondarev.shop.repository.UserRepository;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImp implements AddressService {

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    private DozerBeanMapper mapper;

    public AddressServiceImp(UserRepository userRepository, AddressRepository addressRepository, DozerBeanMapper mapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    @Loggable
    @Transactional
    public Address addNewAddress(AddressDto addressDto, long userId) {
        Address address = this.mapAddressDtoToAddress(addressDto);
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
        return this.mapAddressToAddressDto(address);
    }

    @Override
    @Loggable
    @Transactional
    public Address editAddress(AddressDto addressDto) {
        Address address = this.mapAddressDtoToAddress(addressDto);
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


// ============== NON-API ============

    private AddressDto mapAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        mapper.map(address, addressDto);
        return addressDto;
    }

    private Address mapAddressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        mapper.map(addressDto, address);
        return address;
    }
}
