package com.develop.jina1.basket;

import com.develop.jina1.basket.productRef.BasketProductRef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("basket")
public class Basket {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    private boolean active = true;

    @Column("basket_id")
    private Set<BasketProductRef> products;

    public void addProduct(BasketProductRef basketProductRef) {
        products.add(basketProductRef);
    }

    public void removeProduct(BasketProductRef basketProductRef) {
        products.remove(basketProductRef);
    }

    public Basket(Long userId) {
        this.userId = userId;
    }
}
