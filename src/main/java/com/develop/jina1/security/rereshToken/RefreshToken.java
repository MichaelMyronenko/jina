package com.develop.jina1.security.rereshToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("refresh_token")
public class RefreshToken {

    @Id
    Long Id;

    @Column("user_id")
    Long userId;

    @Column("uu_id")
    String uuId;

    public RefreshToken(String uuId) {
        this.uuId = uuId;
    }

    public RefreshToken(Long userId, String uuId) {
        this.userId = userId;
        this.uuId = uuId;
    }
}
