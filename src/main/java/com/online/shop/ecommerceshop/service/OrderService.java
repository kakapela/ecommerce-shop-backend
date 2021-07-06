package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.Order;

public interface OrderService {

    Order create(Order order);

    void update(Order order);

    Iterable<Order> getAllOrders();
}
