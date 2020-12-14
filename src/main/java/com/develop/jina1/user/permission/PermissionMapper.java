package com.develop.jina1.user.permission;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper
public interface PermissionMapper {
    Set<Permission> mapToEntitySet(Set<PermissionDto> permissionDtoSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissionEnum", source = "permission")
    Permission mapToEntity(PermissionDto permissionDto);
}
