package com.develop.jina1.user;

import com.develop.jina1.user.permission.Permission;
import com.develop.jina1.user.permission.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    private Role role;

    @Column("user_id")
    private Set<Permission> permissions;

    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User(Long id, String role, List<String> permissions) {
        this.id = id;
        this.role = Role.valueOf(role);
        this.permissions = permissions.stream()
                .map(PermissionEnum::valueOf)
                .map(Permission::new)
                .collect(Collectors.toSet());
    }
}
