package com.develop.jina1.adminPanel.category.categoryCharacteristic;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CategoryCharValueMapper {

    CategoryCharValueDto mapToDto(CategoryCharValue categoryCharValue);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    CategoryCharValue mapToEntity(CategoryCharValueCommand categoryCharValueCommand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    void updateEntity(@MappingTarget CategoryCharValue characteristicValue,
                      CategoryCharValueCommand characteristicValueCommand);
}
