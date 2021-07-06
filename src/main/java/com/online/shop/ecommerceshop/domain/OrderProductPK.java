package com.online.shop.ecommerceshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

import static javax.persistence.FetchType.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderProductPK implements Serializable {

    @JsonBackReference
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
