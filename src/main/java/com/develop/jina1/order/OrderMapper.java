package com.develop.jina1.order;

import com.develop.jina1.basket.productRef.BasketProductRef;
import com.develop.jina1.order.productRef.OrderProductRef;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {
    OrderDto mapToDto(Order order);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderProductRef mapBasketProdRefToOrderProdRef(BasketProductRef basketProductRef);
}
