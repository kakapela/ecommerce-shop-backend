package com.online.shop.ecommerceshop.exception.domain;


import static com.online.shop.ecommerceshop.exception.ExceptionHandling.ID_TOO_LONG;

public class IdLengthException extends Exception {
    public IdLengthException(String id) {
        super(String.format(ID_TOO_LONG, id));
    }
}
