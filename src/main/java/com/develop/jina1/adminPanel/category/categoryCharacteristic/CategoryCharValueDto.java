package com.develop.jina1.adminPanel.category.categoryCharacteristic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCharValueDto {
    private Long id;
    private String value;
    private Long characteristicId;
}
