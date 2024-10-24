package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestUserRoleDto;
import com.example.E_com.entity.UserRole;
import com.example.E_com.repo.UserRoleRepository;
import com.example.E_com.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public void create(RequestUserRoleDto requestUserRoleDto) {
        UserRole userRole = UserRole.builder()
                .roleId(UUID.randomUUID().toString())
                .roleName(requestUserRoleDto.getRoleName())
                .roleDescription(requestUserRoleDto.getRoleDescription())
                .build();

        userRoleRepository.save(userRole);
    }
}
