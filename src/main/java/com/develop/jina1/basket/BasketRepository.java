package com.develop.jina1.basket;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BasketRepository extends QuerydslJdbcRepository<Basket, Long> {
    @Query("SELECT basket.user_id AS user_id " +
            "FROM basket WHERE user_id = :userId;")
    Optional<Basket> findByUserId(@Param("userId") Long userId);
}
