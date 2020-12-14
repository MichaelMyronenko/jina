package com.develop.jina1.adminPanel.category.categoryCharacteristic;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryCharValueRepository extends QuerydslJdbcRepository<CategoryCharValue, Long> {
    List<CategoryCharValue> findAll();

    @Query("SELECT category_characteristic_value.id AS id, " +
            "category_characteristic_value.value AS value, " +
            "category_characteristic_value.category_id AS category_id, " +
            "category_characteristic_value.characteristic_id AS characteristic_id " +
            "FROM category_characteristic_value " +
            "WHERE category_characteristic_value.characteristic_id = :characteristicId " +
            "AND category_characteristic_value.category_id = :categoryId")
    Optional<CategoryCharValue> findByCategoryIdAndCharacteristicId(@Param("categoryId") Long categoryId,
                                                                    @Param("characteristicId") Long CharacteristicId);

    @Query("SELECT category_characteristic_value.id AS id, " +
            "category_characteristic_value.value AS value, " +
            "category_characteristic_value.category_id AS category_id, " +
            "category_characteristic_value.characteristic_id AS characteristic_id " +
            "FROM category_characteristic_value " +
            "WHERE category_characteristic_value.id = :characteristicValueId " +
            "AND category_characteristic_value.category_id = :categoryId")
    Optional<CategoryCharValue> findByCategoryIdAndId(@Param("categoryId") Long categoryId,
                                                      @Param("characteristicValueId") Long characteristicValueId);

    @Query("SELECT category_characteristic_value.id AS id, " +
            "category_characteristic_value.value AS value, " +
            "category_characteristic_value.category_id AS category_id, " +
            "category_characteristic_value.characteristic_id AS characteristic_id " +
            "FROM category_characteristic_value " +
            "WHERE category_characteristic_value.category_id = :categoryId")
    List<CategoryCharValue> findAllByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT category_characteristic_value.id AS id, " +
            "category_characteristic_value.value AS value, " +
            "category_characteristic_value.category_id AS category_id, " +
            "category_characteristic_value.characteristic_id AS characteristic_id " +
            "FROM category_characteristic_value " +
            "WHERE category_characteristic_value.characteristic_id = :characteristicId " +
            "AND category_characteristic_value.category_id = :categoryId " +
            "AND category_characteristic_value.value = :value")
    Optional<CategoryCharValue> findByCategoryIdAndCharacteristicIdAndValue(@Param("categoryId") Long categoryId,
                                                                            @Param("characteristicId") Long CharacteristicId,
                                                                            @Param("value") String value);
}
