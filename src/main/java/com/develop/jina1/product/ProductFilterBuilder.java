package com.develop.jina1.product;

import com.develop.jina1.product.productCharacteristic.QProdCharValue;
import com.develop.jina1.utils.OptionalBooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ProductFilterBuilder {
    private final QProduct qProduct = QProduct.product;
    private final QProdCharValue qProdCharValue = QProdCharValue.prodCharValue;
    private final SQLQueryFactory sqlQueryFactory;
    private final Long NON_EXISTENT_ID = -1L;

    public Predicate build(ProductFilteringCommand productFilteringCommand) {
        return new OptionalBooleanBuilder(qProduct.id.isNotNull())
                .notNullAnd(qProduct.price::gt, productFilteringCommand.getMinPrice())
                .notNullAnd(qProduct.price::lt, productFilteringCommand.getMaxPrice())
                .notNullAnd(qProduct.categoryId::eq, productFilteringCommand.getCategoryId())
                .notEmptyAnd(qProduct.name::like, productFilteringCommand.getTitle())
                .notEmptyAnd(qProduct.id::in, getProdIdsByCharValIds(productFilteringCommand.getCategoryCharIdList()))
                .build();
    }

    List<Long> getProdIdsByCharValIds(List<Long> charValueIds) {
        if (nonNull(charValueIds) && !charValueIds.isEmpty()) {
            List<Long> prodIds = sqlQueryFactory
                    .select(qProdCharValue.id)
                    .from(qProdCharValue)
                    .where(qProdCharValue.id.in(charValueIds))
                    .fetch();
            if (prodIds.isEmpty()) {
                return List.of(NON_EXISTENT_ID);
            }
            return prodIds;
        }
        return null;
    }

    public ProductFilterBuilder(SQLQueryFactory sqlQueryFactory) {
        this.sqlQueryFactory = sqlQueryFactory;
    }
}