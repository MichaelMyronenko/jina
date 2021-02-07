package com.develop.jina1.product;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProductCommand {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
