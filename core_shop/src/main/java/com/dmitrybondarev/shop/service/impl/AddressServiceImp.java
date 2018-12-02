package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.repository.api.AddressRepo;
import com.dmitrybondarev.shop.repository.api.UserRepo;
import com.dmitrybondarev.shop.service.api.AddressService;
import com.dmitrybondarev.shop.util.aspect.Loggable;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImp implements AddressService {

    private UserRepo userRepo;

    private AddressRepo addressRepo;

    private DozerBeanMapper mapper;

    public AddressServiceImp(UserRepo userRepo, AddressRepo addressRepo, DozerBeanMapper mapper) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.mapper = mapper;
    }

    @Override
    @Loggable
    @Transactional
    public Address addNewAddress(AddressDto addressDto, long userId) {
        Address address = this.mapAddressDtoToAddress(addressDto);
        address.setId(null);
        User user = userRepo.findById(userId);
        user.getAddresses().add(address);
        userRepo.update(user);
        addressRepo.save(address);
        return address;
    }

    @Override
    @Loggable
    @Transactional
    public AddressDto getAddressDtoById(long id) {
        Address address = addressRepo.findById(id);
        return this.mapAddressToAddressDto(address);
    }

    @Override
    @Loggable
    @Transactional
    public Address editAddress(AddressDto addressDto) {
        Address address = this.mapAddressDtoToAddress(addressDto);
        addressRepo.update(address);
        return address;
    }

    @Override
    @Loggable
    @Transactional
    public void deleteAddress(long addressId, long userId) {

        Address address = addressRepo.findById(addressId);

        User user = userRepo.findById(userId);
        user.getAddresses().remove(address);

        userRepo.update(user);
        addressRepo.deleteById(addressId);
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
