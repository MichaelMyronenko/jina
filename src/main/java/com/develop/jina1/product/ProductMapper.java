package com.develop.jina1.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product mapDtoToEntity(ProductCommand productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void dtoToEntity(ProductCommand productCommand, @MappingTarget Product product);

    ProductDto mapEntityToDto(Product product);
}
