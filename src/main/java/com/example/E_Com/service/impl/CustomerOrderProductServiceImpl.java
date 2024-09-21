package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.dto.response.ResponseCustomerOrderProductDto;
import com.example.E_com.entity.Customer;
import com.example.E_com.entity.CustomerOrder;
import com.example.E_com.entity.CustomerOrderProduct;
import com.example.E_com.entity.Product;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.CustomerOrderProductRepository;
import com.example.E_com.repo.ProductRepository;
import com.example.E_com.service.CustomerOrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerOrderProductServiceImpl implements CustomerOrderProductService {
    private final CustomerOrderProductRepository customerOrderProductRepository;

    @Override
    public void create(List<RequestCustomerOrderProductDto> requestCustomerOrderProducts, CustomerOrder customerOrder, List<Product> validProducts) {
        for (int i = 0; i < requestCustomerOrderProducts.size(); i++) {
            RequestCustomerOrderProductDto orderProduct = requestCustomerOrderProducts.get(i);
            Product product = validProducts.get(i);  // Use the valid product from the list

            CustomerOrderProduct customerOrderProduct = CustomerOrderProduct.builder()
                    .customerOrder(customerOrder)
                    .qty(orderProduct.getQty())
                    .product(product)  // Use the product directly
                    .build();

            customerOrderProductRepository.save(customerOrderProduct);
        }
    }

    @Override
    public List<ResponseCustomerOrderProductDto> getById(String id) {
        List<CustomerOrderProduct> customerOrderProducts = customerOrderProductRepository.findByCustomerOrder_PropertyId(id);

        return customerOrderProducts.stream().map(this::toResponseCustomerOrderProductDto).toList();
    }

    private ResponseCustomerOrderProductDto toResponseCustomerOrderProductDto(CustomerOrderProduct customerOrderProduct){
        return ResponseCustomerOrderProductDto.builder()
                .propertyId(customerOrderProduct.getProduct().toString())
                .qty(customerOrderProduct.getQty())
                .build();
    }
}
