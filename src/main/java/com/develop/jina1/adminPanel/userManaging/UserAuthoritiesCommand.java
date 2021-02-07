package com.develop.jina1.adminPanel.userManaging;

import com.develop.jina1.user.Role;
import com.develop.jina1.user.permission.PermissionDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthoritiesCommand {
    private Set<PermissionDto> permissions;
    private Role role;
}
