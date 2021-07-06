package com.online.shop.ecommerceshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Basic(optional = false)
    private String name;

    private BigDecimal price;

    private String pictureUrl;

    private Double size;

    private String category;

    private String color;

    private String brand;

    private String description;

}
