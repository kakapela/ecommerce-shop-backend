package com.online.shop.ecommerceshop.mapper;

import com.online.shop.ecommerceshop.domain.OrderProduct;
import com.online.shop.ecommerceshop.dto.OrderProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Mapper(componentModel = "spring")
public abstract class OrderProductMapper {

    @Mapping(target = "totalPrice", expression = "java(getTotalPrice(orderProduct))")
    public abstract OrderProductDto mapToDto(OrderProduct orderProduct);

    public BigDecimal getTotalPrice(OrderProduct orderProduct) {
        return orderProduct.getProduct().getPrice().multiply(valueOf(orderProduct.getQuantity()));
    }

}
