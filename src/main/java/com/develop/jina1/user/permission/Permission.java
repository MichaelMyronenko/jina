package com.develop.jina1.user.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("permission")
public class Permission {
    @Id
    private Long id;
    @Column("permission")
    private PermissionEnum permissionEnum;

    public Permission(PermissionEnum permissionEnum) {
        this.permissionEnum = permissionEnum;
    }
}
