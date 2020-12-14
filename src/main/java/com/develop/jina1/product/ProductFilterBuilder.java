package com.develop.jina1.product;

import com.develop.jina1.utils.OptionalBooleanBuilder;
import com.querydsl.core.types.Predicate;

public class ProductFilterBuilder {
    private final QProduct qProduct = QProduct.product;

    public Predicate build(ProductFilteringCommand productFilteringCommand) {
        return new OptionalBooleanBuilder(qProduct.id.eq(1L))
                .notNullAnd(qProduct.price::gt, productFilteringCommand.getMinPrice())
                .notNullAnd(qProduct.price::lt, productFilteringCommand.getMaxPrice())
                .notNullAnd(qProduct.categoryId::eq, productFilteringCommand.getCategoryId())
                .notEmptyAnd(qProduct.prodCharValues.any().categoryCharacteristicValueId::in, productFilteringCommand.getCategoryCharIdList())
                .build();
    }
}