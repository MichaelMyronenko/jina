package com.develop.jina1.product.productCharacteristic;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdCharValueRepository extends QuerydslJdbcRepository<ProdCharValue, Long> {
    List<ProdCharValue> findAll();

    @Query("SELECT product_characteristic_value.id AS id, " +
            "product_characteristic_value.value AS value, " +
            "product_characteristic_value.product AS product, " +
            "category_characteristic_value_id AS category_characteristic_value_id " +
            "FROM product_characteristic_value " +
            "WHERE product_characteristic_value.product = :productId")
    List<ProdCharValue> findAllByProductId(@Param("productId") Long productId);

    @Query("SELECT product_characteristic_value.id AS id," +
            "product_characteristic_value.value AS value, " +
            "product_characteristic_value.product AS product, " +
            "category_characteristic_value_id AS category_characteristic_valueId " +
            "FROM product_characteristic_value " +
            "WHERE product_characteristic_value.product = :productId " +
            "AND product_characteristic_value.id = :valueId")
    Optional<ProdCharValue> findByProductIdAndId(@Param("productId") Long productId,
                                                 @Param("valueId") Long valueId);

    @Query("SELECT product_characteristic_value.id AS id, " +
            "product_characteristic_value.value AS value, " +
            "product_characteristic_value.product AS product, " +
            "category_characteristic_value_id AS category_characteristic_value_id " +
            "FROM product_characteristic_value " +
            "LEFT JOIN category_characteristic_value " +
            "ON product_characteristic_value.category_characteristic_value_id = category_characteristic_value.id " +
            "WHERE characteristic_id = :characteristicId " +
            "AND product = :productId")
    Optional<ProdCharValue> findByCharacteristicIdAndProductId(@Param("characteristicId") Long characteristicId,
                                                               @Param("productId") Long productId);

    @Query("SELECT product_characteristic_value.id AS id, " +
            "product_characteristic_value.value AS value, " +
            "product_characteristic_value.product AS product, " +
            "category_characteristic_value_id AS category_characteristic_value_id " +
            "FROM product_characteristic_value " +
            "WHERE product_characteristic_value.product = :productId " +
            "AND product_characteristic_value.category_characteristic_value_id = :categoryCharacteristicValueId")
    Optional<ProdCharValue> findByProductIdAndCategoryCharacteristicValueId(@Param("productId") Long productId,
                                                                            @Param("categoryCharacteristicValueId") Long characteristicValue);
}
