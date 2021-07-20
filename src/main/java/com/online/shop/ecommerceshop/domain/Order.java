package com.online.shop.ecommerceshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    @OneToMany(mappedBy = "pk.order", fetch = LAZY)
    private List<OrderProduct> orderProducts;

    @Transient
    public BigDecimal getTotalOrderPrice() {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        List<OrderProduct> orderProducts = getOrderProducts();

        for (OrderProduct op : orderProducts) {

            sum =  sum.add(op.getTotalPrice());
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

}
