package com.online.shop.ecommerceshop.exception.domain;

import static com.online.shop.ecommerceshop.exception.ExceptionHandling.PRODUCT_CATEGORY_WAS_NOT_FOUND;

public class ProductCategoryNotFoundException extends Exception {
    public ProductCategoryNotFoundException() {
        super(PRODUCT_CATEGORY_WAS_NOT_FOUND);
    }
}
