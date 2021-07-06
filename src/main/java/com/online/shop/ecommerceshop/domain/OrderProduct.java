package com.online.shop.ecommerceshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDERS_PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    public OrderProduct(Order order, Product product, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public BigDecimal getTotalPrice() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }
}
