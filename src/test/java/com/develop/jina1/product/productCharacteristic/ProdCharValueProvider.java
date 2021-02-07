package com.develop.jina1.product.productCharacteristic;

public class ProdCharValueProvider {
    public static ProdCharValueCommand getProdCharValueCommand() {
        return ProdCharValueCommand.builder()
                .categoryCharacteristicValueId(3L)
                .value("24")
                .build();
    }

    public static ProdCharValueCommand getSecProdCharValueCommand() {
        return ProdCharValueCommand.builder()
                .categoryCharacteristicValueId(1L)
                .value("Intel core I7 84329")
                .build();
    }
}
