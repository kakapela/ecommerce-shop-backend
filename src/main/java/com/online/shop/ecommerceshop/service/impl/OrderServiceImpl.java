package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.repository.OrderRepository;
import com.online.shop.ecommerceshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    //TODO - @VALID
    //TODO 2 - LocalDate czy Date czy Instant?

    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return null;
    }
}
