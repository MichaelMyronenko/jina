package com.develop.jina1.product.additionalCharacteristic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("additional_characteristic")
public class AdditionalCharacteristic {
    private Long characteristicId;
    private String value;
}