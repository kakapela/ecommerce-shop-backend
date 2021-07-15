package com.online.shop.ecommerceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String currency;
    private String description;
    private String size;
    private String color;
    private String brand;
    private String category;
    private String subcategory;
    private List<String> pictureUrls;
}
