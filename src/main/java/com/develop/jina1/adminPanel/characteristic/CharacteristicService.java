package com.develop.jina1.adminPanel.characteristic;

import com.develop.jina1.error.ConflictException;
import com.develop.jina1.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;
    private final CharacteristicMapper characteristicMapper;

    public CharacteristicDto getCharacteristic(Long characteristicId) {
        return characteristicMapper.mapToDto(processCharacteristic(characteristicId));
    }

    public CharacteristicDto saveCharacteristic(CharacteristicCommand characteristicCommand) {
        throwConflictIfCharacteristicExists(characteristicCommand.getName());
        Characteristic characteristic = characteristicMapper.mapToEntity(characteristicCommand);
        return characteristicMapper.mapToDto(characteristicRepository.save(characteristic));
    }

    public CharacteristicDto updateCharacteristic(Long characteristicId,
                                                  CharacteristicCommand characteristicCommand) {
        Characteristic characteristic = processCharacteristic(characteristicId);
        if (!(characteristic.getName().equals(characteristicCommand.getName()))) {
            throwConflictIfCharacteristicExists(characteristicCommand.getName());
        }
        characteristicMapper.updateEntity(characteristic, characteristicCommand);
        return characteristicMapper.mapToDto(characteristicRepository.save(characteristic));
    }

    public Characteristic processCharacteristic(Long characteristicId){
        return characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new NotFoundException("Not found characteristic with id " + characteristicId));
    }

    private boolean isCharacteristicExistent(String characteristicName) {
        return characteristicRepository.existsByName(characteristicName);
    }

    private void throwConflictIfCharacteristicExists(String characteristicName) {
        if (isCharacteristicExistent(characteristicName)) {
            throw new ConflictException("Characteristic with this name already exists!");
        }
    }
}
