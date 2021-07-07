package com.online.shop.ecommerceshop.exception.domain;


import static com.online.shop.ecommerceshop.exception.ExceptionHandling.NO_USER_FOUND_BY_ID;
import static com.online.shop.ecommerceshop.exception.ExceptionHandling.USER_WAS_NOT_FOUND;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super(USER_WAS_NOT_FOUND);
    }

    public UserNotFoundException(String userId) {
        super(NO_USER_FOUND_BY_ID + userId);
    }
}
