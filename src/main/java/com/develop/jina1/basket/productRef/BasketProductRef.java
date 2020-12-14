package com.develop.jina1.basket.productRef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("basket_product")
@Getter
@Setter
@AllArgsConstructor
public class BasketProductRef {
    @Id
    private Long id;
    @Column("product_id")
    private Long productId;
    @Column("basket_id")
    private Long basketId;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketProductRef that = (BasketProductRef) o;
        return productId.equals(that.productId) &&
                basketId.equals(that.basketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, basketId);
    }
}
