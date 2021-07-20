package com.online.shop.ecommerceshop.repository;

import com.online.shop.ecommerceshop.domain.OrderProduct;
import com.online.shop.ecommerceshop.domain.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
}
