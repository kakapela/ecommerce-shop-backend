package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = {  "/byid" })
    public ResponseEntity<ProductDto>getProductsById(@RequestParam long id) throws ResourceNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id)) ;
    }
}
