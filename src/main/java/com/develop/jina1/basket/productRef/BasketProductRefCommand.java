package com.develop.jina1.basket.productRef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasketProductRefCommand {
    Long productId;
    Integer quantity;
}
