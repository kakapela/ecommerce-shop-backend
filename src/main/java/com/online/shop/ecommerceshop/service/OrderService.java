package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.dto.OrderDto;
import com.online.shop.ecommerceshop.mapper.OrderMapper;
import com.online.shop.ecommerceshop.mapper.OrderProductMapper;
import com.online.shop.ecommerceshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;


    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders(){

        List<Order> xd = orderRepository.findAll();


        List<OrderDto> collect = orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToDto)
                .collect(toList());
        System.out.println("xd");
        return null;
    }

}
