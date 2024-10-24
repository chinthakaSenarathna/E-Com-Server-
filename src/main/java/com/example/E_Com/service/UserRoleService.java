package com.example.E_com.service;

import com.example.E_com.dto.request.RequestUserRoleDto;

public interface UserRoleService {
    public void create(RequestUserRoleDto requestUserRoleDto);
    public void initializerUserRoles();
}
