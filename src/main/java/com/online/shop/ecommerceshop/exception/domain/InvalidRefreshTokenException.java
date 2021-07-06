package com.online.shop.ecommerceshop.exception.domain;

import static com.online.shop.ecommerceshop.exception.ExceptionHandling.REFRESH_TOKEN_IS_INVALID;

public class InvalidRefreshTokenException extends Exception{

    public InvalidRefreshTokenException() {
        super(REFRESH_TOKEN_IS_INVALID) ;
    }
}
