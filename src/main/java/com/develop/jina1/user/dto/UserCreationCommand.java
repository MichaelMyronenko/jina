package com.develop.jina1.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationCommand {
    private String username;

    private String password;
}
