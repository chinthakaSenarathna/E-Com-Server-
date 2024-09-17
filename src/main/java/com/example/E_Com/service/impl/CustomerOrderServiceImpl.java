package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.dto.response.ResponseCustomerOrderDto;
import com.example.E_com.dto.response.ResponseCustomerOrderProductDto;
import com.example.E_com.dto.response.paginate.CustomerOrderPaginateDto;
import com.example.E_com.entity.Customer;
import com.example.E_com.entity.CustomerOrder;
import com.example.E_com.entity.CustomerOrderProduct;
import com.example.E_com.entity.Product;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.exception.ProductNotAvailableException;
import com.example.E_com.repo.CustomerOrderProductRepository;
import com.example.E_com.repo.CustomerOrderRepository;
import com.example.E_com.repo.CustomerRepository;
import com.example.E_com.repo.ProductRepository;
import com.example.E_com.service.CustomerOrderProductService;
import com.example.E_com.service.CustomerOrderService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerOrderProductService customerOrderProductService;
    private final EntityManager entityManager;

    @Override
    public void create(RequestCustomerOrderDto requestCustomerOrderDto) {
        Optional<Customer> selectedCustomer = customerRepository.findById(requestCustomerOrderDto.getCustomer());

        if (selectedCustomer.isEmpty()) {
            throw new EntryNotFoundException("Customer does not exist...");
        }

        List<RequestCustomerOrderProductDto> orderProducts = requestCustomerOrderDto.getOrderProducts();
        List<Product> validProducts = checkAvailability(orderProducts);  // Get valid products

        CustomerOrder customerOrder = CustomerOrder.builder()
                .propertyId(UUID.randomUUID().toString())
                .createdDate(requestCustomerOrderDto.getCreatedDate())
                .paymentType(requestCustomerOrderDto.getPaymentType())
                .totalAmount(requestCustomerOrderDto.getTotalAmount())
                .customer(selectedCustomer.get())
                .build();

        customerOrderRepository.save(customerOrder);

        customerOrderProductService.create(orderProducts, customerOrder, validProducts);

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

    private List<Product> checkAvailability(List<RequestCustomerOrderProductDto> orderProducts){
        List<Product> validProducts = new ArrayList<>();

        for(RequestCustomerOrderProductDto orderProduct : orderProducts) {
            Optional<Product> selectedProduct = productRepository.findById(orderProduct.getPropertyId());

            if (selectedProduct.isEmpty()) {
                throw new EntryNotFoundException("Product does not exist...");
            }

            Product product = selectedProduct.get();

            if (!product.isAvailable() || orderProduct.getQty() > product.getQty()) {
                throw new ProductNotAvailableException("Product is not available or quantity is insufficient...");
            }

            validProducts.add(product);
        }

        return validProducts;
    }

}
