package com.example.E_Com.service.impl;

import com.example.E_Com.dto.request.RequestCustomerDto;
import com.example.E_Com.dto.response.ResponseCustomerDto;
import com.example.E_Com.dto.response.paginate.CustomerPaginateDto;
import com.example.E_Com.entity.Customer;
import com.example.E_Com.repo.CustomerRepository;
import com.example.E_Com.service.CustomerService;
import com.example.E_Com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    // when project is run, customerRepository assign as instance
    private final CustomerRepository customerRepository;

    @Override
    public void create(RequestCustomerDto requestCustomerDto) {
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

    @Override
    public ResponseCustomerDto getById(String id) {
        // avoid the NullPointException -> if not exist data, given null
        Optional<Customer> selectedCustomer = customerRepository.findById(id);

        if(selectedCustomer.isEmpty()){
            throw new RuntimeException("Customer Not Found");
        }

        return toResponseCustomerDto(selectedCustomer.get());
    }

    @Override
    public void update(String id, RequestCustomerDto requestCustomerDto) {
        Optional<Customer> selectedCustomer = customerRepository.findById(id);

        if(selectedCustomer.isEmpty()){
            throw new RuntimeException("Customer not Exist");
        }

        Customer customer = selectedCustomer.get();

        customer.setName(requestCustomerDto.getName());
        customer.setEmail(requestCustomerDto.getEmail());
        customer.setPhoneNo(requestCustomerDto.getPhoneNo());
        customer.setAddress(requestCustomerDto.getAddress());
        customer.setActive(requestCustomerDto.isActive());

        customerRepository.save(customer);
    }

    @Override
    public CustomerPaginateDto getAll(String searchText, int page, int size) {
        return null;
    }

    // mapped the given Customer Entity with ResponseCustomerDto
    private ResponseCustomerDto toResponseCustomerDto(Customer customer){
        return ResponseCustomerDto.builder()
                .propertyId(customer.getPropertyId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNo(customer.getPhoneNo())
                .address(customer.getAddress())
                .isActive(customer.isActive())
                .build();
    }
}
