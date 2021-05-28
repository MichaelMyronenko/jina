package com.develop.jina1.security.rereshToken;

import com.develop.jina1.error.UnauthorizedException;
import com.develop.jina1.security.JwtTokenProvider;
import com.develop.jina1.security.TokenDto;
import com.develop.jina1.security.userLogin.AuthenticatedUser;
import com.develop.jina1.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository tokenRepository;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public TokenDto refreshAccessToken(RefreshTokenCommand tokenCommand) {
        RefreshToken refreshToken = tokenProvider.parseRefreshToken(tokenCommand.getRefreshToken()).get();
        Optional<RefreshToken> refreshTokenFromDb = tokenRepository.findByUuId(refreshToken.getUuId());

        if (refreshTokenFromDb.isPresent()) {
            AuthenticatedUser principal = new AuthenticatedUser(userService
                    .processUser(refreshTokenFromDb.get().getUserId()));

            tokenRepository.delete(refreshTokenFromDb.get());

            String newRefreshTokenUuId = UUID.randomUUID().toString();
            String newRefreshToken = tokenProvider.createRefreshToken(newRefreshTokenUuId, principal);

            refreshToken = new RefreshToken(principal.getId(), newRefreshTokenUuId);
            tokenRepository.save(refreshToken);

            return new TokenDto(tokenProvider.createAccessToken(principal), newRefreshToken);
        } else {
            throw new UnauthorizedException("Refresh token is invalid!");
        }
    }
}
