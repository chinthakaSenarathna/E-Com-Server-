package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.response.ResponseCustomerOrderDto;
import com.example.E_com.dto.response.paginate.CustomerOrderPaginateDto;
import com.example.E_com.service.CustomerOrderService;

public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Override
    public void create(RequestCustomerOrderDto requestCustomerOrderDto) {

    }

    @Override
    public ResponseCustomerOrderDto getById(String id) {
        return null;
    }

    @Override
    public void update(String id, ResponseCustomerOrderDto responseCustomerOrderDto) {

    }

    @Override
    public CustomerOrderPaginateDto getAll(String id, int page, int size) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
