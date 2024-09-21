package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.dto.response.ResponseCustomerOrderDto;
import com.example.E_com.dto.response.ResponseCustomerOrderProductDto;
import com.example.E_com.dto.response.paginate.CustomerOrderPaginateDto;
import com.example.E_com.entity.Customer;
import com.example.E_com.entity.CustomerOrder;
import com.example.E_com.entity.Product;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.exception.ProductNotAvailableException;
import com.example.E_com.repo.CustomerOrderRepository;
import com.example.E_com.repo.CustomerRepository;
import com.example.E_com.repo.ProductRepository;
import com.example.E_com.service.CustomerOrderProductService;
import com.example.E_com.service.CustomerOrderService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
        Optional<CustomerOrder> selectedCustomerOrder = customerOrderRepository.findById(id);

        if(selectedCustomerOrder.isEmpty()){
            throw new EntryNotFoundException("Customer Order Not Exist...");
        }

        List<ResponseCustomerOrderProductDto> customerOrderProducts = customerOrderProductService.getById(id);

        return toResponseCustomerOrderDto(selectedCustomerOrder.get(),customerOrderProducts);
    }

    @Override
    public void update(String id, ResponseCustomerOrderDto responseCustomerOrderDto) {

    }

    @Override
    public CustomerOrderPaginateDto getAll(String id, int page, int size) {
        Optional<Customer> selectedCustomer = customerRepository.findById(id);

        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Exist...");
        }

        return CustomerOrderPaginateDto.builder()
                .count(customerOrderRepository.countAllWithSearchText(id))
                .dataList(customerOrderRepository.findAllWithSearchText(id, PageRequest.of(page,size))
                        .stream().map(e -> {
                            List<ResponseCustomerOrderProductDto> o= customerOrderProductService.getById(e.getPropertyId());
                            return toResponseCustomerOrderDto(e,o);
                        }).toList())
                .build();
    }

    @Override
    public void delete(String id) {
        customerOrderRepository.deleteById(id);
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

    private ResponseCustomerOrderDto toResponseCustomerOrderDto(CustomerOrder customerOrder, List<ResponseCustomerOrderProductDto> responseCustomerOrderProductDto){
        return ResponseCustomerOrderDto.builder()
                .propertyId(customerOrder.getPropertyId())
                .createdDate(customerOrder.getCreatedDate())
                .totalAmount(customerOrder.getTotalAmount())
                .orderProducts(responseCustomerOrderProductDto)
                .paymentType(customerOrder.getPaymentType())
                .customer(customerOrder.getCustomer().toString())
                .build();
    }

}
