package com.example.E_com.repo;

import com.example.E_com.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface UserRoleHasUserRepository extends JpaRepository<UserRoleHasUser, String> {
    // this 'findBy'+'User' has 'User' in UserRoleHasUser model attribute... -> user
    List<UserRoleHasUser> findByUser(String user);
}
