package com.develop.jina1.security;

import com.develop.jina1.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.time.Instant.now;

@Component
@EnableConfigurationProperties(SecurityProps.class)
public class JwtTokenProvider {

    private static final String ID_PARAM = "id";
    private static final String ROLE_PARAM = "role";
    private static final String PERMISSIONS_PARAM = "permissions";

    private final SecurityProps securityProps;

    public JwtTokenProvider(SecurityProps securityProps) {
        this.securityProps = securityProps;
    }

    public Optional<AuthenticatedUser> parseUser(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(securityProps.getSignature().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            final AuthenticatedUser user = new AuthenticatedUser(
                    new User(
                    claims.get(ID_PARAM, Long.class),
                    claims.get(ROLE_PARAM, String.class),
                    claims.get(PERMISSIONS_PARAM, List.class)
                    )
            );
            return Optional.of(user);
        } catch (JwtException e) {
            return Optional.empty();
        }
    }

    public String createToken(AuthenticatedUser user) {
        return Jwts.builder()
                .claim(ID_PARAM, user.getId())
                .claim(ROLE_PARAM, user.getRole())
                .claim(PERMISSIONS_PARAM, user.getPermissions())
                .signWith(hmacShaKeyFor(securityProps.getSignature().getBytes()))
                .setExpiration(Date.from(now().plus(securityProps.getLifetime())))
                .compact();
    }
}
