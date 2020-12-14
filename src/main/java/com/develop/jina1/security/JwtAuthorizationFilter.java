package com.develop.jina1.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String AUTH_TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(AUTHORIZATION);

        if (header != null && header.startsWith(AUTH_TOKEN_PREFIX)) {
            final String token = header.substring(AUTH_TOKEN_PREFIX.length());
            jwtTokenProvider.parseUser(token)
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
                    .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
        }

        filterChain.doFilter(request, response);
    }
}
