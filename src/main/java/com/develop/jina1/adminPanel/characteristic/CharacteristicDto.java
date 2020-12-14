package com.develop.jina1.adminPanel.characteristic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDto {
    private Long id;
    private String name;
    private boolean isAdditional = false;
}
