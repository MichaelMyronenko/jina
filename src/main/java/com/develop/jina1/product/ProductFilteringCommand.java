package com.develop.jina1.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilteringCommand {
    private List<Long> categoryCharIdList;
    private Double minPrice;
    private Double maxPrice;
    private String title;
    private Long categoryId;
}
