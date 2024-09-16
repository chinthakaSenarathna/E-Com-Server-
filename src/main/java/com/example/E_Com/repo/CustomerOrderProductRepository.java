package com.example.E_com.repo;

import com.example.E_com.entity.CustomerOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderProductRepository extends JpaRepository<CustomerOrderProduct, String> {
}
