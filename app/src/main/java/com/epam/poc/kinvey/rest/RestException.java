package com.epam.poc.kinvey.rest;

class RestException extends Exception {
    private static final long serialVersionUID = -7064438379376463935L;

    RestException(String detailMessage, Throwable e) {
        super(detailMessage, e);
    }
}
