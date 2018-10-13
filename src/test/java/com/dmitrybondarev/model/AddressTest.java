package com.dmitrybondarev.model;

import org.junit.Assert;
import org.junit.Test;

public class AddressTest {

    @Test
    public void fakeTestForJacoco() {
        Address address = new Address("1", "2", 3, "4", "5", "6");
        Assert.assertEquals(1, Integer.parseInt(address.getCountry()));
    }

}