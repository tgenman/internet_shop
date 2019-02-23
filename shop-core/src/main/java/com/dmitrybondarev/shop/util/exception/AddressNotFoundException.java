package com.dmitrybondarev.shop.util.exception;

public class AddressNotFoundException extends Exception {

    private static final long serialVersionUID = 5861334153366287163L;

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(final String message) {
        super(message);
    }

    public AddressNotFoundException(final Throwable cause) {
        super(cause);
    }
}
