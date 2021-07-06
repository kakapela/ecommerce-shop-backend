package com.online.shop.ecommerceshop.exception.domain;


import static com.online.shop.ecommerceshop.exception.ExceptionHandling.NO_USER_FOUND_BY_ID;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId) {
        super(NO_USER_FOUND_BY_ID + userId);
    }
}
