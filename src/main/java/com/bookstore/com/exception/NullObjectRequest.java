package com.bookstore.com.exception;

public class NullObjectRequest extends RuntimeException {

    String message;

    public NullObjectRequest() {
    }

    public NullObjectRequest(String message) {
        super(message);
        this.message = message;

    }
}
