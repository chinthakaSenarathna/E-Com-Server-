package com.example.E_com.repo;

import com.example.E_com.dto.response.ResponseCustomerOrderProductDto;
import com.example.E_com.entity.CustomerOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderProductRepository extends JpaRepository<CustomerOrderProduct, String> {
    List<CustomerOrderProduct> findByCustomerOrder_PropertyId(String customerOrderId);
}
