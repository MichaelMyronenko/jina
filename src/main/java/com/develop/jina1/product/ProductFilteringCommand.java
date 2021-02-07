package com.develop.jina1.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFilteringCommand {
    private List<Long> categoryCharIdList;
    private Double minPrice;
    private Double maxPrice;
    private String title;
    private Long categoryId;
}
