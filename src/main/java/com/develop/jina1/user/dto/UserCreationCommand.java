package com.develop.jina1.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreationCommand {
    private String username;

    private String password;
}
