package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Address;
import com.dmitrybondarev.model.User;
import com.dmitrybondarev.model.dto.AddressDto;
import com.dmitrybondarev.repository.api.AddressRepo;
import com.dmitrybondarev.repository.api.UserRepo;
import com.dmitrybondarev.service.api.AddressService;
import lombok.extern.log4j.Log4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j
@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private DozerBeanMapper mapper;



    @Override
    @Transactional
    public Address addNewAddress(AddressDto addressDto, long userId) {
        Address address = this.mapAddressDtoToAddress(addressDto);
        addressRepo.saveAddress(address);
        User user = userRepo.findById(userId);
        user.getAddresses().add(address);
        userRepo.updateUser(user);
        return address;
    }

    @Override
    @Transactional
    public Address getAddressDtoById(long id) {
        log.info("getAddressDtoById id = " + id + " start.");
        Address address = addressRepo.findById(id);
        log.info("getAddressDtoById id = " + id + " end.");
        return address;
    }

    @Override
    @Transactional
    public Address editAddress(AddressDto addressDto) {
        log.info("editAddress start.");
        Address address = this.mapAddressDtoToAddress(addressDto);
        addressRepo.updateAddress(address);
        log.info("editAddress end.");
        return address;
    }

    @Override
    @Transactional
    public void deleteAddress(long addressId, long userId) {
        log.info("deleteAddress start.");

        Address address = addressRepo.findById(addressId);

        User user = userRepo.findById(userId);
        user.getAddresses().remove(address);

        addressRepo.removeById(addressId);
        log.info("deleteAddress end.");
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
