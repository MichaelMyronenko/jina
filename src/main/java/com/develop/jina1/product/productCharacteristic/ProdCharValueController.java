package com.develop.jina1.product.productCharacteristic;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products/{productId}/characteristicValues")
public class ProdCharValueController {
    private final ProdCharValueService characteristicValueService;
    
    @GetMapping
    ResponseEntity<List<ProdCharValueDto>> getAllCharacteristicValuesByCategory(@PathVariable Long productId) {
        return new ResponseEntity<>(characteristicValueService.getProdCharValues(productId), HttpStatus.OK);
    }

    @GetMapping("/{characteristicValueId}}")
    ResponseEntity<ProdCharValueDto> getCharacteristicValuesByCategory(@PathVariable Long productId,
                                                                       @PathVariable Long characteristicValueId) {
        return new ResponseEntity<>(characteristicValueService
                .getProdCharValue(productId, characteristicValueId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ProdCharValueDto> createCharacteristicValue(@PathVariable Long productId,
                                                               @RequestBody ProdCharValueCommand characteristicValueCommand) {
        return new ResponseEntity<>(characteristicValueService.saveProdCharValue(productId, characteristicValueCommand), HttpStatus.CREATED);
    }

    @PutMapping("/{characteristicValueId}")
    ResponseEntity<ProdCharValueDto> updateCharacteristicValue(@PathVariable Long productId,
                                                               @PathVariable Long characteristicValueId,
                                                               @RequestBody ProdCharValueCommand categoryCharacteristicValueCommand) {
        return new ResponseEntity<>(characteristicValueService
                .updateProdCharValue(productId, characteristicValueId, categoryCharacteristicValueCommand), HttpStatus.OK);
    }
}
