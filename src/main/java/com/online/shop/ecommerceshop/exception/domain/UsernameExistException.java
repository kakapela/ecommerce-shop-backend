package com.online.shop.ecommerceshop.exception.domain;


import static com.online.shop.ecommerceshop.constant.UserImplConstant.USERNAME_ALREADY_EXISTS;

public class UsernameExistException extends Exception{
    public UsernameExistException() {
        super(USERNAME_ALREADY_EXISTS);
    }
}
