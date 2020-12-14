package com.develop.jina1.user;

import com.develop.jina1.user.dto.UserCreationCommand;
import com.develop.jina1.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    UserDto entityToDto(User authenticatedUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    User dtoToEntity(UserCreationCommand userCreationCommand);
}
