package com.develop.jina1.product.productCharacteristic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdCharValueCommand {
    private String value;
    private Long categoryCharacteristicValueId;
}
