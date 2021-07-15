package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.Picture;
import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.repository.ProductRepository;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    public ProductDto getProductById(long id) throws ResourceNotFoundException {

        Product product = productRepository.findByIdAndFetchPictures(id).orElseThrow(ResourceNotFoundException::new);

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .description(product.getDescription())
                .size(product.getSize())
                .color(product.getColor())
                .brand(product.getBrand())
                .category(product.getCategory())
                .subcategory(product.getSubcategory())
                .pictureUrls(
                        product.getPictures().stream().map(Picture::getUrl).collect(toList())
                )
                .build();
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
