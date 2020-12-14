package com.develop.jina1.adminPanel.category.categoryCharacteristic;

import com.develop.jina1.adminPanel.characteristic.Characteristic;
import com.develop.jina1.adminPanel.characteristic.CharacteristicService;
import com.develop.jina1.error.ConflictException;
import com.develop.jina1.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryCharValueService {
    private final CategoryCharValueRepository categoryCharValueRepository;
    private final CategoryCharValueMapper categoryCharValueMapper;
    private final CharacteristicService characteristicService;

    public CategoryCharValueDto getCharacteristicValue(Long categoryId, Long characteristicValueId) {
        return categoryCharValueMapper
                .mapToDto(processCharacteristicValueById(categoryId, characteristicValueId));
    }

    public List<CategoryCharValueDto> getAllCharacteristicValues(Long categoryId) {
        return categoryCharValueRepository.findAllByCategoryId(categoryId)
                .stream().map(categoryCharValueMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryCharValueDto createCharacteristicValue(Long categoryId,
                                                          CategoryCharValueCommand charValueCommand) {
        Characteristic characteristic = characteristicService.processCharacteristic(charValueCommand.getCharacteristicId());
        throwConflictExceptionIfValueExists(categoryId, charValueCommand);
        throwConflictIfCharacteristicIsAdditional(characteristic);

        CategoryCharValue characteristicValue = categoryCharValueMapper
                .mapToEntity(charValueCommand);
        characteristicValue.setCategoryId(categoryId);
        return categoryCharValueMapper.mapToDto(categoryCharValueRepository.save(characteristicValue));
    }

    public CategoryCharValueDto updateCategoryCharacteristicValue(Long categoryId,
                                                                  Long characteristicValueId,
                                                                  CategoryCharValueCommand characteristicValueCommand) {
        CategoryCharValue characteristicValue = processCharacteristicValueById(categoryId, characteristicValueId);
        throwConflictExceptionIfValueExists(categoryId, characteristicValueCommand);

        categoryCharValueMapper.updateEntity(characteristicValue, characteristicValueCommand);
        characteristicValue = categoryCharValueRepository.save(characteristicValue);
        return categoryCharValueMapper.mapToDto(characteristicValue);
    }

    private boolean checkCharacteristicValueExiting(Long categoryId,
                                                    Long characteristicId,
                                                    String value) {
        return categoryCharValueRepository.findByCategoryIdAndCharacteristicIdAndValue(categoryId, characteristicId, value)
                .isPresent();
    }

    private void throwConflictExceptionIfValueExists(Long categoryId,
                                                     CategoryCharValueCommand characteristicValueCommand) {
        if (checkCharacteristicValueExiting(categoryId,
                characteristicValueCommand.getCharacteristicId(),
                characteristicValueCommand.getValue())) {
            throw new ConflictException("Characteristic value already exists!");
        }
    }

    private void throwConflictIfCharacteristicIsAdditional(Characteristic characteristic) {
        if (characteristic.isAdditional()) {
            throw new ConflictException("The characteristic is additional!");
        }
    }

    public CategoryCharValue processCharacteristicValue(Long characteristicValueId) {
        return categoryCharValueRepository.findById(characteristicValueId)
                .orElseThrow(() -> new NotFoundException("Not found characteristic value with id " + characteristicValueId));
    }

    private CategoryCharValue processCharacteristicValueById(Long categoryId,
                                                             Long characteristicValueId) {
        return categoryCharValueRepository.findByCategoryIdAndId(categoryId, characteristicValueId)
                .orElseThrow(() -> new NotFoundException("Not found characteristic value!"));
    }
}
