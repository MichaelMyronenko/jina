package com.develop.jina1.product;

public class ProductProvider {
    public static ProductFilteringCommand getEmptyFilterCommand() {
        return ProductFilteringCommand.builder().build();
    }

    public static ProductFilteringCommand getFilledFilterCommand() {
        return ProductFilteringCommand.builder()
                .categoryId(1L)
                .maxPrice(1000D)
                .minPrice(500D)
                .build();
    }

    public static ProductCommand getSmartphoneProductCommand() {
        return ProductCommand.builder()
                .categoryId(2L)
                .name("IPhone")
                .description("Some Iphone")
                .price(1000D)
                .build();
    }

    public static ProductCommand getHouseholdProductCommand() {
        return ProductCommand.builder()
                .categoryId(4L)
                .name("Zinger")
                .description("Some scissors")
                .price(50D)
                .build();
    }
}
