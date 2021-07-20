package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>>getProducts() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.getAllProducts()) ;
    }

    @GetMapping(value = {  "/{id}" })
    public ResponseEntity<ProductDto>getProductsById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id)) ;
    }
}
