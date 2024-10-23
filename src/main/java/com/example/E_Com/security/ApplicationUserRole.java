package com.example.E_com.security;

import java.util.Set;

public enum ApplicationUserRole {
    USER(),
    CUSTOMER(),
    ADMIN(),
    MANAGER();

    private final Set<ApplicationUserPermission> applicationUserPermissions;
}
