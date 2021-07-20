package com.online.shop.ecommerceshop.dto;

import com.online.shop.ecommerceshop.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private LocalDate dateCreated;
    private String status;

    private BigDecimal totalPrice;

    private List<OrderProductDto> orderedProducts;
}
