package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.dto.ProductListDto;
import com.online.shop.ecommerceshop.enumeration.ProductCategory;
import com.online.shop.ecommerceshop.exception.domain.ProductCategoryNotFoundException;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.mapper.ProductMapper;
import com.online.shop.ecommerceshop.repository.ProductRepository;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ProductListDto getProductListPaginated(Pageable pageable, String category) throws ProductCategoryNotFoundException {

        Page<Product> pageProducts;

        if (category == null) {
            pageProducts = productRepository.findAllProductsPaginated(pageable);
        } else {
            List<String> productCategories = stream(ProductCategory.values())
                    .map(ProductCategory::toString)
                    .collect(toList());

            if (!productCategories.contains(category))
                throw new ProductCategoryNotFoundException();

            pageProducts = productRepository.findByCategory(category, pageable);
        }

        List<ProductDto> products = pageProducts.getContent()
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());

        return ProductListDto.builder()
                .totalItems(pageProducts.getTotalElements())
                .totalPages(pageProducts.getTotalPages())
                .currentPage(pageProducts.getNumber())
                .products(products)
                .build();
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
