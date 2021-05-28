package com.develop.jina1.product.productCharacteristic;

import com.develop.jina1.adminPanel.category.categoryCharacteristic.CategoryCharValue;
import com.develop.jina1.adminPanel.category.categoryCharacteristic.CategoryCharValueService;
import com.develop.jina1.error.ConflictException;
import com.develop.jina1.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdCharValueService {
    private final CategoryCharValueService categoryCharValueService;
    private final ProdCharValueRepository prodCharValueRepository;
    private final ProdCharValueMapper characteristicValueMapper;

    public List<ProdCharValueDto> getProdCharValues(Long productId) {
        return prodCharValueRepository.findAllByProductId(productId)
                .stream().map(characteristicValueMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public ProdCharValueDto getProdCharValue(Long productId,
                                             Long characteristicValueId) {
        return characteristicValueMapper.mapToDto(processProdCharValue(productId, characteristicValueId));
    }

    public ProdCharValueDto saveProdCharValue(Long productId,
                                              ProdCharValueCommand characteristicValueCommand) {

        CategoryCharValue categoryCharValue = categoryCharValueService
                .processCharacteristicValue(characteristicValueCommand
                        .getCategoryCharacteristicValueId());

        throwConflictIfProdCharValueExists(categoryCharValue.getCharacteristicId(), productId);

        ProdCharValue characteristicValue = characteristicValueMapper.mapToEntity(characteristicValueCommand);
        characteristicValue.setProductId(productId);

        characteristicValue = prodCharValueRepository.save(characteristicValue);
        return characteristicValueMapper.mapToDto(characteristicValue);
    }

    public ProdCharValueDto updateProdCharValue(Long productId,
                                                Long pCValueId,
                                                ProdCharValueCommand characteristicValueCommand) {
        ProdCharValue pcValue = processProdCharValue(productId, pCValueId);
        characteristicValueMapper.updateEntity(pcValue, characteristicValueCommand);
        return characteristicValueMapper.mapToDto(prodCharValueRepository.save(pcValue));
    }

    private ProdCharValue processProdCharValue(Long productId, Long characteristicValueId) {
        return prodCharValueRepository.findByProductIdAndId(productId, characteristicValueId)
                .orElseThrow(() -> new NotFoundException("Not found product characteristic value!"));
    }

    private boolean checkProdCharValueExistent(Long characteristicId, Long productId) {
        return prodCharValueRepository.findByCharacteristicIdAndProductId(characteristicId, productId).isPresent();
    }

    private void throwConflictIfProdCharValueExists(Long characteristicId, Long productId) {
        if (checkProdCharValueExistent(characteristicId, productId)) {
            throw new ConflictException("The product characteristic already exists!");
        }
    }
}
