package com.online.shop.ecommerceshop.repository;

import com.online.shop.ecommerceshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.pictures WHERE p.id = :id")
    Optional<Product> findByIdAndFetchPictures(Long id);

    @Query(value = "SELECT p FROM Product p LEFT JOIN FETCH p.pictures order by p.createdDate",
            countQuery = "select count(p) from Product p"
    )
    Page<Product> findAllProductsPaginated(Pageable pageable);

    @Query(value = "SELECT p FROM Product p LEFT JOIN FETCH p.pictures" +
            " where p.category = :category order by p.createdDate",
            countQuery = "select count(p) from Product p where p.category = :category"
    )
    Page<Product> findByCategory(String category, Pageable pageable);
}
