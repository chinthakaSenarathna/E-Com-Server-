package com.example.E_com.repo;

import com.example.E_com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {
    // this 'findBy'+'Email' has 'Email' is attribute of User model... -> email
    Optional<User> findByEmail(String email);
}
