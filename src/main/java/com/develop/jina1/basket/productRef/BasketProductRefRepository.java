package com.develop.jina1.basket.productRef;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketProductRefRepository extends QuerydslJdbcRepository<BasketProductRef, Long> {
    @Query("SELECT basket_product.product_id AS product_id, " +
            "basket_product.quantity AS quantity, " +
            "basket_product.basket_id AS basket_id " +
            "FROM basket_product " +
            "WHERE product_id = :productId " +
            "AND basket_id = :basketId")
    Optional<BasketProductRef> findByProductIdAndBasket(@Param("productId") Long productId,
                                                        @Param("basketId") Long basketId);

    @Modifying
    @Query("UPDATE basket_product b " +
            "SET quantity = :quantity " +
            "WHERE b.basket_id = :basketId " +
            "AND b.product_id = :productId;")
    void update(@Param("basketId") Long basketIf,
                @Param("productId") Long productId,
                @Param("quantity") Integer quantity);
}
