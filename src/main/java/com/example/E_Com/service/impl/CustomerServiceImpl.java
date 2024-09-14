package com.example.E_Com.service.impl;

import com.example.E_Com.dto.request.RequestCustomerDto;
import com.example.E_Com.entity.Customer;
import com.example.E_Com.repo.CustomerRepository;
import com.example.E_Com.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    // when project is run, customerRepository assign as instance
    private final CustomerRepository customerRepository;

//    @Autowired
//    public CustomerServiceImpl(CustomerRepository customerRepository){
//        this.customerRepository = customerRepository;
//    }

    @Override
    public void create(RequestCustomerDto requestCustomerDto) {

        System.out.println(requestCustomerDto.isActive());

        Customer customer = Customer.builder()
                .propertyId(UUID.randomUUID().toString())
                .name(requestCustomerDto.getName())
                .email(requestCustomerDto.getEmail())
                .phoneNo(requestCustomerDto.getPhoneNo())
                .address(requestCustomerDto.getAddress())
                .isActive(requestCustomerDto.isActive())
                .build();

        customerRepository.save(customer);
    }
}
