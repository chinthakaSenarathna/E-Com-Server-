package com.example.E_com.api;

import com.example.E_com.dto.request.RequestCustomerDto;
import com.example.E_com.service.CustomerService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerDto requestCustomerDto){
        customerService.create(requestCustomerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer was Created!...", null),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id, @RequestBody RequestCustomerDto requestCustomerDto){
        customerService.update(id, requestCustomerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer was Updated!...", null),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id){
        customerService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Customer was Deleted!...", null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer was founded", customerService.getById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer List...", customerService.getAll(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
