package com.develop.jina1.product.productCharacteristic;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProdCharValueMapper {
    ProdCharValueDto mapToDto(ProdCharValue characteristicValue);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", ignore = true)
    ProdCharValue mapToEntity(ProdCharValueCommand characteristicValueCommand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", ignore = true)
    void updateEntity(@MappingTarget ProdCharValue characteristicValue,
                      ProdCharValueCommand characteristicValueCommand);
}
