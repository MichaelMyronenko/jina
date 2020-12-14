package com.develop.jina1.adminPanel.userManaging;

import com.develop.jina1.security.AuthenticatedUser;
import com.develop.jina1.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin-panel/users")
@AllArgsConstructor
public class AdminPanelUserController {

    private final AdminPanelUserService adminPanelUserService;

    @PutMapping("/{userId}/authorities")
    @PreAuthorize("hasAnyAuthority({T(com.develop.jina1.user.Role).ADMINISTRATOR," +
            "T(com.develop.jina1.user.permission.PermissionEnum).MANAGE_USER_PERMISSIONS," +
            "T(com.develop.jina1.user.permission.PermissionEnum).MANAGE_USER_ROLES })")
    public ResponseEntity<UserDto> changeUserAuthorities(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                         @PathVariable Long userId,
                                                         @RequestBody UserAuthoritiesCommand userAuthoritiesCommand) {
        return new ResponseEntity<>(adminPanelUserService
                .manageUserAuthorities(authenticatedUser, userId, userAuthoritiesCommand), HttpStatus.OK);
    }
}
