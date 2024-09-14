package com.example.E_Com.service;

import com.example.E_Com.dto.request.RequestCustomerDto;
import com.example.E_Com.dto.response.ResponseCustomerDto;
import com.example.E_Com.dto.response.paginate.CustomerPaginateDto;
import com.example.E_Com.util.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public void create(RequestCustomerDto requestCustomerDto);
    public ResponseCustomerDto getById(String id);
    public void update(String id, RequestCustomerDto requestCustomerDto);
    public CustomerPaginateDto getAll(String searchText, int page, int size);
    public void delete(String id);
}
