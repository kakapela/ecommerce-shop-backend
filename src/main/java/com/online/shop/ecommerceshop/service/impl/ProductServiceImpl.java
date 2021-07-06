package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.repository.ProductRepository;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
