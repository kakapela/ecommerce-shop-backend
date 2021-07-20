package com.online.shop.ecommerceshop.mapper;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderDto mapToDto(Order order);
}
