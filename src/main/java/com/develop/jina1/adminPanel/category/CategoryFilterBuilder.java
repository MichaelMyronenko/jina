package com.develop.jina1.adminPanel.category;

import com.develop.jina1.utils.OptionalBooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

@Component
public class CategoryFilterBuilder {
    private final QCategory qCategory = QCategory.category;

    public Predicate build(CategoryFilterCommand categoryFilterCommand) {
        return new OptionalBooleanBuilder(qCategory.id.isNotNull())
                .notEmptyAnd(qCategory.title::like, categoryFilterCommand.getTitle())
                .notNullAnd(qCategory.id::eq, categoryFilterCommand.getId())
                .build();
    }
}
