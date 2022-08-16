package com.example.backend.security;

import com.google.common.collect.Sets;

import static com.example.backend.security.ApplicationUserPermission.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(
            BOOK_GET, BOOK_POST, BOOK_PUT, BOOK_DELETE,
            CATEGORY_GET, CATEGORY_POST, CATEGORY_PUT, CATEGORY_DELETE,
            AUTHOR_GET, AUTHOR_POST, AUTHOR_PUT, AUTHOR_DELETE)),

    USER(Sets.newHashSet(
            BOOK_GET, CATEGORY_GET));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getpermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
