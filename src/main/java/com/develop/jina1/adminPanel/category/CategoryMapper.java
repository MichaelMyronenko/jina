package com.develop.jina1.adminPanel.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category mapToEntity(CategoryCommand categoryCommand);

    CategoryDto mapToDto(Category category);

    @Mapping(target = "id", ignore = true)
    void updateEntity(CategoryCommand categoryCommand, @MappingTarget Category category);
}
