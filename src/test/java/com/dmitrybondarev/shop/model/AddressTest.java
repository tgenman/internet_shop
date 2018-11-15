package com.dmitrybondarev.shop.model;

import org.junit.Assert;
import org.junit.Test;

public class AddressTest {

    @Test
    public void fakeTestForJacoco() {
        Address address = new Address();
        address.setCountry("1");
        Assert.assertEquals(1, Integer.parseInt(address.getCountry()));
    }

}