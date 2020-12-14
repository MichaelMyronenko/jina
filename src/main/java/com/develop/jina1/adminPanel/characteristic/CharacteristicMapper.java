package com.develop.jina1.adminPanel.characteristic;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CharacteristicMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryCharValues", ignore = true)
    Characteristic mapToEntity(CharacteristicCommand characteristicCommand);

    CharacteristicDto mapToDto(Characteristic characteristic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryCharValues", ignore = true)
    void updateEntity(@MappingTarget Characteristic characteristic, CharacteristicCommand characteristicCommand);
}
