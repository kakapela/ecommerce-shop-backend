package com.online.shop.ecommerceshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    private LocalDateTime createdDate;

    @OneToMany(mappedBy="product", fetch = LAZY)
    private List<Picture> pictures;
}
