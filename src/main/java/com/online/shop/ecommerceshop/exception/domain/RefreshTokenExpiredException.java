package com.online.shop.ecommerceshop.exception.domain;

import static com.online.shop.ecommerceshop.exception.ExceptionHandling.REFRESH_TOKEN_EXPIRED;

public class RefreshTokenExpiredException extends  Exception{
    public RefreshTokenExpiredException() {
        super(REFRESH_TOKEN_EXPIRED);
    }
}
