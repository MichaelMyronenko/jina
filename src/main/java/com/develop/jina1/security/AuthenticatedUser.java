package com.develop.jina1.security;

import com.develop.jina1.user.permission.PermissionEnum;
import com.develop.jina1.user.Role;
import com.develop.jina1.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthenticatedUser implements UserDetails {
    
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRole()));
        authorities.addAll(getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        return authorities;
    }

    public boolean hasRole(Role role) {
        return getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .stream().anyMatch(authority -> authority.equals(role.name()));
    }

    public boolean hasPermission(PermissionEnum permission) {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(permission.name()));
    }

    public String getRole() {
        return user.getRole().name();
    }

    public List<String> getPermissions() {
        return user.getPermissions()
                .stream()
                .map(permission -> permission.getPermissionEnum().toString())
                .collect(Collectors.toList());
    }


    public Long getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
