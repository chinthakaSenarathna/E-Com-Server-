package com.example.E_com.service;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.response.ResponseCustomerOrderDto;
import com.example.E_com.dto.response.paginate.CustomerOrderPaginateDto;
import com.example.E_com.dto.response.paginate.CustomerPaginateDto;

public interface CustomerOrderService {
    public void create(RequestCustomerOrderDto requestCustomerOrderDto);
    public ResponseCustomerOrderDto getById(String id);
    public void update(String id,ResponseCustomerOrderDto responseCustomerOrderDto);
    public CustomerOrderPaginateDto getAll(String id, int page, int size);
    public void delete(String id);
}
