package com.develop.jina1.product;

import com.develop.jina1.product.productCharacteristic.ProdCharValue;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class Product {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("price")
    private Double price;

    @Column("created_at")
    private Instant createdAt;

    @Column("category_id")
    private Long categoryId;

    @Column("product_id")
    private Set<ProdCharValue> prodCharValues;

    public void addProductCharacteristic(ProdCharValue characteristicValue) {
        prodCharValues.add(characteristicValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
