package com.dmitrybondarev.shop.util.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 586131053366287163L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(final String message) {
        super(message);
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }
}
