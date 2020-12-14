package com.develop.jina1.product;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends QuerydslJdbcRepository<Product, Long> {

    @Query("SELECT product.id AS id, " +
            "product.name AS name, " +
            "product.description AS description, " +
            "product.price AS price, " +
            "product.created_at AS created_at, " +
            "product.category_id AS category_id " +
            "FROM product " +
            "WHERE product.category_id = :categoryId " +
            "AND product.id = :id")
    Optional<Product> findByCategoryIdAndId(@Param("categoryId") Long categoryId,
                                            @Param("id") Long id);
}
