package com.example.E_Com.service;

import com.example.E_Com.dto.request.RequestCustomerDto;
import com.example.E_Com.util.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public void create(RequestCustomerDto requestCustomerDto);
    public ResponseEntity<StandardResponse> getById(String id);
}
