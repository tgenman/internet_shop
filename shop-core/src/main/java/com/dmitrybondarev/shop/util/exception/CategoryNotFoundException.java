package com.dmitrybondarev.shop.util.exception;

public class CategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 58613153366287163L;

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(final String message) {
        super(message);
    }

    public CategoryNotFoundException(final Throwable cause) {
        super(cause);
    }
}
