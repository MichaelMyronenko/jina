package com.develop.jina1.user.dto;

import com.develop.jina1.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Long id;

    private Role role;

    private String username;

    private String password;
}
