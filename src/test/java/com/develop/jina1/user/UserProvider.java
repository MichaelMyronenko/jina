package com.develop.jina1.user;

import com.develop.jina1.adminPanel.userManaging.UserAuthoritiesCommand;
import com.develop.jina1.user.dto.UserCreationCommand;
import com.develop.jina1.user.permission.PermissionDto;
import com.develop.jina1.user.permission.PermissionEnum;

import java.util.Set;

import static com.develop.jina1.user.permission.PermissionEnum.*;

public class UserProvider {
    public static UserCreationCommand getUserCreationCommand() {
        return UserCreationCommand.builder()
                .username("testCreationUsername")
                .password("testPassword")
                .build();
    }

    public static UserCreationCommand getExistentUserCreationCommand() {
        return UserCreationCommand.builder()
                .username("testUser")
                .password("testPass")
                .build();
    }

    public static UserAuthoritiesCommand getUserAuthCommand() {
        return UserAuthoritiesCommand.builder()
                .role(Role.ADMINISTRATOR)
                .permissions(Set
                        .of(new PermissionDto(MANAGE_CATEGORY),
                        new PermissionDto(MANAGE_USER_PERMISSIONS),
                        new PermissionDto(MANAGE_USER_ROLES)))
                .build();
    }
}
