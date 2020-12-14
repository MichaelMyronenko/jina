package com.develop.jina1.basket.productRef;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BasketProductRefMapper {
    @Mapping(target = "basketId", ignore = true)
    @Mapping(target = "id", ignore = true)
    BasketProductRef mapToEntity(BasketProductRefCommand basketProductRefCommand);

    BasketProductRefDto mapToDto(BasketProductRef basketProductRef);
}
