package com.online.shop.ecommerceshop.repository;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
