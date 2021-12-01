package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.dto.ProductListDto;
import com.online.shop.ecommerceshop.exception.domain.ProductCategoryNotFoundException;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<ProductListDto> getProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size,
            @RequestParam(required = false) String category
    ) throws ProductCategoryNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductListPaginated(PageRequest.of(page, size), category));
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<ProductDto> getProductsById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }
}
