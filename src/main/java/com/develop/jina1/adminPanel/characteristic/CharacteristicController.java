package com.develop.jina1.adminPanel.characteristic;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/characteristics")
public class CharacteristicController {
    private final CharacteristicService characteristicService;

    @GetMapping("/{characteristicId}")
    public ResponseEntity<CharacteristicDto> getCharacteristic(@PathVariable Long characteristicId) {
        return new ResponseEntity<>(characteristicService.getCharacteristic(characteristicId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CharacteristicDto> createCharacteristic(@RequestBody CharacteristicCommand characteristicCommand) {
        return new ResponseEntity<>(characteristicService.saveCharacteristic(characteristicCommand), HttpStatus.CREATED);
    }

    @PutMapping("/{characteristicId}")
    public ResponseEntity<CharacteristicDto> updateCharacteristic(@PathVariable Long characteristicId,
                                                                  @RequestBody CharacteristicCommand characteristicCommand) {
        return new ResponseEntity<>(characteristicService.updateCharacteristic(characteristicId, characteristicCommand), HttpStatus.OK);
    }
}
