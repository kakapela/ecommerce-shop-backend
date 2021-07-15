package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;

public interface ProductService {

    Iterable<Product> getAllProducts();

    Product getProduct(long id) throws ResourceNotFoundException;

    ProductDto getProductById(long id) throws ResourceNotFoundException;

    Product save(Product product);
}
