package com.example.E_com.service;

import com.example.E_com.dto.request.RequestCustomerDto;
import com.example.E_com.dto.response.ResponseCustomerDto;
import com.example.E_com.dto.response.paginate.CustomerPaginateDto;

public interface CustomerService {
    public void create(RequestCustomerDto requestCustomerDto);
    public ResponseCustomerDto getById(String id);
    public void update(String id, RequestCustomerDto requestCustomerDto);
    public CustomerPaginateDto getAll(String searchText, int page, int size);
    public void delete(String id);
}
