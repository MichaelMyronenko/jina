package com.develop.jina1.adminPanel.userManaging;

import com.develop.jina1.security.AuthenticatedUser;
import com.develop.jina1.user.*;
import com.develop.jina1.user.dto.UserDto;
import com.develop.jina1.user.permission.Permission;
import com.develop.jina1.user.permission.PermissionDto;
import com.develop.jina1.user.permission.PermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.develop.jina1.user.permission.PermissionEnum.*;
import static com.develop.jina1.user.Role.*;

@Service
@AllArgsConstructor
public class AdminPanelUserService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PermissionMapper permissionMapper;
    private final UserMapper userMapper;

    public UserDto manageUserAuthorities(AuthenticatedUser authenticatedUser, Long userId, UserAuthoritiesCommand userAuthoritiesCommand) {
        User user = userService.processUser(userId);
        setPermissions(authenticatedUser, user, userAuthoritiesCommand.getPermissions());
        setRole(authenticatedUser, user, userAuthoritiesCommand.getRole());
        user = userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    private void setPermissions(AuthenticatedUser authenticatedUser, User user, Set<PermissionDto> permissionDtoSet) {
        if (authenticatedUser.hasRole(ADMINISTRATOR) || authenticatedUser.hasPermission(MANAGE_USER_PERMISSIONS)) {
            Set<Permission> permissions = permissionMapper.mapToEntitySet(permissionDtoSet);
            user.setPermissions(permissions);
        }
    }

    private void setRole(AuthenticatedUser authenticatedUser, User user, Role role) {
        if (authenticatedUser.hasRole(ADMINISTRATOR) || authenticatedUser.hasPermission(MANAGE_USER_ROLES)) {
            user.setRole(role);
        }
    }
}
