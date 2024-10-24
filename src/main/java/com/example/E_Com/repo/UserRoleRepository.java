package com.example.E_com.repo;

import com.example.E_com.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query(
            value = "SELECT * FROM user_role WHERE role_name=?1",
            nativeQuery = true
    )
    Optional<UserRole> findUserRoleByRoleName(String role);
}
