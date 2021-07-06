package com.online.shop.ecommerceshop.exception.domain;


import static com.online.shop.ecommerceshop.exception.ExceptionHandling.PRODUCT_NOT_FOUND;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super(PRODUCT_NOT_FOUND);
    }
}
