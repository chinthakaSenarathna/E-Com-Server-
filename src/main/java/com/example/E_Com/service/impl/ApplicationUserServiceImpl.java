package com.example.E_com.service.impl;

import com.example.E_com.auth.ApplicationUser;
import com.example.E_com.entity.User;
import com.example.E_com.entity.UserRoleHasUser;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.UserRepository;
import com.example.E_com.repo.UserRoleHasUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.E_com.security.ApplicationUserRole.*;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleHasUserRepository userRoleHasUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> selectedUser = userRepository.findByEmail(username);

        if(selectedUser.isEmpty()){
            throw new EntryNotFoundException(String.format("username %s not found",username));
        }

        List<UserRoleHasUser> roles = userRoleHasUserRepository.findByUser(selectedUser.get().getUserId());

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        for(UserRoleHasUser u:roles){
            if(u.getUserRole().getRoleName().equals("USER")){
                grantedAuthorities.addAll(USER.grantedAuthorities());
            }
            if(u.getUserRole().getRoleName().equals("CUSTOMER")){
                grantedAuthorities.addAll(CUSTOMER.grantedAuthorities());
            }
            if(u.getUserRole().getRoleName().equals("ADMIN")){
                grantedAuthorities.addAll(ADMIN.grantedAuthorities());
            }
            if(u.getUserRole().getRoleName().equals("MANAGER")){
                grantedAuthorities.addAll(MANAGER.grantedAuthorities());
            }
        }

        return new ApplicationUser(
                grantedAuthorities,
                selectedUser.get().getPassword(),
                selectedUser.get().getEmail(),
                selectedUser.get().isAccountNonExpired(),
                selectedUser.get().isAccountNonLocked(),
                selectedUser.get().isCredentialsNonExpired(),
                selectedUser.get().isEnabled()
        );
    }
}
