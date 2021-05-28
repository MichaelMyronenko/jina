package com.develop.jina1.security.rereshToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenCommand {
    private String refreshToken;
}
