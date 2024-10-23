package com.example.E_com.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet()),
    MANAGER(Sets.newHashSet());

    private final Set<ApplicationUserPermission> applicationUserPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermissions) {
        this.applicationUserPermissions = applicationUserPermissions;
    }

    public Set<ApplicationUserPermission> getApplicationUserPermissions(){
        return applicationUserPermissions;
    }
}
