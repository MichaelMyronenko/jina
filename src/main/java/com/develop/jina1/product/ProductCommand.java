package com.develop.jina1.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductCommand {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
