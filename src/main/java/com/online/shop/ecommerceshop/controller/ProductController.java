package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Iterable<Product> >getProducts() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.getAllProducts()) ;
    }
}
