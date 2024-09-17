package com.example.E_com.service;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.entity.Customer;
import com.example.E_com.entity.CustomerOrder;
import com.example.E_com.entity.Product;

import java.util.List;

public interface CustomerOrderProductService {
    public void create(List<RequestCustomerOrderProductDto> requestCustomerOrderProducts, CustomerOrder customerOrder, List<Product> validProducts);
}
