package com.develop.jina1.order;

import com.develop.jina1.order.productRef.OrderProductRef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("order")
public class Order {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("order_id")
    private Set<OrderProductRef> products;

    @Column("total_price")
    private Double totalPrice;

    @Column("order_status")
    private OrderStatus orderStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}