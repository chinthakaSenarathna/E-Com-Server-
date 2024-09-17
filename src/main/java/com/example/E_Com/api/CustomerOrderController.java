package com.example.E_com.api;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.service.CustomerOrderService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customerOrders")
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerOrderDto requestCustomerOrderDto){
        customerOrderService.create(requestCustomerOrderDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer Order was Created!...",null),
                HttpStatus.CREATED
        );
    }

}
