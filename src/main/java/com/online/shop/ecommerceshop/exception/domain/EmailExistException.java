package com.online.shop.ecommerceshop.exception.domain;

import static com.online.shop.ecommerceshop.constant.UserImplConstant.EMAIL_ALREADY_EXISTS;

public class EmailExistException extends Exception{
    public EmailExistException() {
        super(EMAIL_ALREADY_EXISTS);
    }
}
