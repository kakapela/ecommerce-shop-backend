package com.online.shop.ecommerceshop.repository;

import com.online.shop.ecommerceshop.domain.RefreshToken;
import com.online.shop.ecommerceshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);

    @Query("SELECT rt from RefreshToken rt join fetch rt.user where rt.token = ?1")
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("DELETE FROM RefreshToken rt where rt.user = ?1")
    void deleteByUser(User user);

}
