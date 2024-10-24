package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestUserRoleDto;
import com.example.E_com.entity.UserRole;
import com.example.E_com.exception.DuplicateEntryException;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.UserRoleRepository;
import com.example.E_com.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public void create(RequestUserRoleDto requestUserRoleDto) {
        Optional<UserRole> selectedUserRole = userRoleRepository.findUserRoleByRoleName(requestUserRoleDto.getRoleName());

        if(selectedUserRole.isPresent()){
            throw new DuplicateEntryException("role already exists");
        }

        UserRole userRole = UserRole.builder()
                .roleId(UUID.randomUUID().toString())
                .roleName(requestUserRoleDto.getRoleName())
                .roleDescription(requestUserRoleDto.getRoleDescription())
                .build();

        userRoleRepository.save(userRole);
    }

    @Override
    public void initializerUserRoles() {
        List<UserRole> roleList = userRoleRepository.findAll();

        if(roleList.isEmpty()){
            userRoleRepository.saveAll(List.of(
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("USER")
                            .roleDescription("description1")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("CUSTOMER")
                            .roleDescription("description2")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("ADMIN")
                            .roleDescription("description3")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("MANAGER")
                            .roleDescription("description4")
                            .build()
            ));
        }
    }
}
