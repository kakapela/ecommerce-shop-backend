package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id) throws ResourceNotFoundException;

    Product save(Product product);
}
