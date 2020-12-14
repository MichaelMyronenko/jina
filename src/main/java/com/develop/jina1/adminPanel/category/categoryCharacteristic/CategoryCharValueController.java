package com.develop.jina1.adminPanel.category.categoryCharacteristic;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories/{categoryId}/characteristicValues")
public class CategoryCharValueController {
    private final CategoryCharValueService characteristicValueService;

    @GetMapping
    ResponseEntity<List<CategoryCharValueDto>> getAllCharacteristicValuesByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(characteristicValueService.getAllCharacteristicValues(categoryId), HttpStatus.OK);
    }

    @GetMapping("/{characteristicValueId}}")
    ResponseEntity<CategoryCharValueDto> getCharacteristicValuesByCategory(@PathVariable Long categoryId,
                                                                           @PathVariable Long characteristicValueId) {
        return new ResponseEntity<>(characteristicValueService
                .getCharacteristicValue(categoryId, characteristicValueId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CategoryCharValueDto> createCharacteristicValue(@PathVariable Long categoryId,
                                                                   @RequestBody CategoryCharValueCommand charValueCommand) {
        return new ResponseEntity<>(characteristicValueService.createCharacteristicValue(categoryId, charValueCommand), HttpStatus.CREATED);
    }

    @PutMapping("/{characteristicValueId}")
    ResponseEntity<CategoryCharValueDto> updateCharacteristicValue(@PathVariable Long categoryId,
                                                                   @PathVariable Long characteristicValueId,
                                                                   @RequestBody CategoryCharValueCommand categoryCharValueCommand) {
        return new ResponseEntity<>(characteristicValueService
                .updateCategoryCharacteristicValue(categoryId, characteristicValueId, categoryCharValueCommand), HttpStatus.OK);
    }
}
