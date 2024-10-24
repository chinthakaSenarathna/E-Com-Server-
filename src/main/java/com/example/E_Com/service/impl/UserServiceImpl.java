package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestUserDto;
import com.example.E_com.dto.request.RequestUserRoleDto;
import com.example.E_com.entity.User;
import com.example.E_com.entity.UserRole;
import com.example.E_com.entity.UserRoleHasUser;
import com.example.E_com.exception.DuplicateEntryException;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.UserRepository;
import com.example.E_com.repo.UserRoleHasUserRepository;
import com.example.E_com.repo.UserRoleRepository;
import com.example.E_com.service.UserRoleService;
import com.example.E_com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleHasUserRepository userRoleHasUserRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void create(RequestUserDto requestUserDto,String roleType) {
        Optional<User> selectedUser = userRepository.findByEmail(requestUserDto.getEmail());

        if(selectedUser.isPresent()){
            throw new DuplicateEntryException("user already exists");
        }

        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(requestUserDto.getEmail())
                .displayName(requestUserDto.getDisplayName())
                .password(passwordEncoder.encode(requestUserDto.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        Optional<UserRole> userRole = userRoleRepository.findUserRoleByRoleName(roleType);

        if(userRole.isEmpty()){
            throw new EntryNotFoundException("role not founded");
        }

        userRepository.save(user);

        UserRoleHasUser userRoleHasUser = UserRoleHasUser.builder()
                .user(user)
                .userRole(userRole.get())
                .build();

        userRoleHasUserRepository.save(userRoleHasUser);

    }
}
