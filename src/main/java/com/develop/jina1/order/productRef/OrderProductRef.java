package com.develop.jina1.order.productRef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("order_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRef {
    @Id
    Long id;
    @Column("order_id")
    private Long orderId;
    @Column("column_id")
    private Long productId;
    private Integer quantity;
}
