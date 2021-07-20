package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.mapper.ProductMapper;
import com.online.shop.ecommerceshop.repository.ProductRepository;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(long id) throws ResourceNotFoundException {
        Product product = productRepository.findByIdAndFetchPictures(id).orElseThrow(ResourceNotFoundException::new);
        return productMapper.mapToDto(product);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
