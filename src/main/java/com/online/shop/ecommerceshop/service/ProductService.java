package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.dto.ProductListDto;
import com.online.shop.ecommerceshop.exception.domain.ProductCategoryNotFoundException;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductListDto getProductListPaginated(Pageable pageable, String category) throws ProductCategoryNotFoundException;

    ProductDto getProductById(long id) throws ResourceNotFoundException;

    Product save(Product product);
}
