package com.online.shop.ecommerceshop.repository;

import com.online.shop.ecommerceshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @Query("SELECT o from Order o LEFT JOIN FETCH o.orderProducts")
    List<Order> findAll();
}
