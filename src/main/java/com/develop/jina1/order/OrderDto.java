package com.develop.jina1.order;

import com.develop.jina1.order.productRef.OrderProductRefDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private OrderStatus orderStatus;
    private Set<OrderProductRefDto> products;
}
