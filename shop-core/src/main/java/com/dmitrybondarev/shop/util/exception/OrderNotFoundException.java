package com.dmitrybondarev.shop.util.exception;

public class OrderNotFoundException extends RuntimeException  {

    private static final long serialVersionUID = 5861315366287163L;

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(final String message) {
        super(message);
    }

    public OrderNotFoundException(final Throwable cause) {
        super(cause);
    }
}
