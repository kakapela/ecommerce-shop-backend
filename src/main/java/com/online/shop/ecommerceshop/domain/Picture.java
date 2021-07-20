package com.online.shop.ecommerceshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PICTURES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String url;

    public Picture(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="product_id", nullable=true)
    private Product product;
}
