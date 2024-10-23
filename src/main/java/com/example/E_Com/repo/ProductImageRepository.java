package com.example.E_com.repo;

import com.example.E_com.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}
