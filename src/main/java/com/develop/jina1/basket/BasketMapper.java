package com.develop.jina1.basket;

import com.develop.jina1.basket.productRef.BasketProductRefMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = BasketProductRefMapper.class)
public interface BasketMapper {

    @Mapping(target = "total_price", ignore = true)
    BasketDto mapToDto(Basket basket);
}