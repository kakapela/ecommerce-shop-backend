package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }



}
