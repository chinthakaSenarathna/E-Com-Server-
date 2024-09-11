package com.example.E_Com.repo;

import com.example.E_Com.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<Customer, String> {       // <Entity, Data type of primary key>
    
}
