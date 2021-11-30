package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.dto.ProductDto;
import com.online.shop.ecommerceshop.dto.ProductListDto;
import com.online.shop.ecommerceshop.exception.domain.ResourceNotFoundException;
import com.online.shop.ecommerceshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping()
    public ResponseEntity<ProductListDto>getProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductListPaginated(PageRequest.of(page, size)));
    }

    @GetMapping(value = {  "/{id}" })
    public ResponseEntity<ProductDto>getProductsById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id)) ;
    }
}
