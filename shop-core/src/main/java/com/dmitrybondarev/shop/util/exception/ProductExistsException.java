package com.dmitrybondarev.shop.util.exception;

public class ProductExistsException extends Exception  {

    public ProductExistsException(final String message) {
        super(message);
    }
}
