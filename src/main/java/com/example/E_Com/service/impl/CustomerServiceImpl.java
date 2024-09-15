package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestCustomerDto;
import com.example.E_com.dto.response.ResponseCustomerDto;
import com.example.E_com.dto.response.paginate.CustomerPaginateDto;
import com.example.E_com.entity.Customer;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.CustomerRepository;
import com.example.E_com.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
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
        Optional<Customer> selectedCustomer = customerRepository.findById(id);

        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer is Not Exist...");
        }

        return toResponseCustomerDto(selectedCustomer.get());
    }

    @Override
    public void update(String id, RequestCustomerDto requestCustomerDto) {
        Optional<Customer> selectedCustomer = customerRepository.findById(id);

        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer is Not Exist");
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
        return CustomerPaginateDto.builder()
                .count(customerRepository.countAllWithSearchText(searchText))
                .dataList(customerRepository.findAllWithSearchText(searchText, PageRequest.of(page,size))
                        .stream().map(this::toResponseCustomerDto).toList())
                .build();
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

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
