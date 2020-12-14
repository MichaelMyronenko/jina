package com.develop.jina1.basket;

import com.develop.jina1.basket.productRef.BasketProductRefDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Set<BasketProductRefDto> products;

    private Double total_price;
}
