package com.online.shop.ecommerceshop.enumeration;

public enum ProductCategory {
    MEN, WOMEN, CHILDREN;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
