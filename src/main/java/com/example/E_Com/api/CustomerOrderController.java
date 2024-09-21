package com.example.E_com.api;

import com.example.E_com.dto.request.RequestCustomerOrderDto;
import com.example.E_com.dto.request.RequestCustomerOrderProductDto;
import com.example.E_com.service.CustomerOrderService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id){
        customerOrderService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Customer Order was Deleted...", null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer Order was Found...", customerOrderService.getById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String id,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer Order List...", customerOrderService.getAll(id, page, size)),
                HttpStatus.OK
        );
    }

}
