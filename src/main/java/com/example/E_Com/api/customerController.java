package com.example.E_Com.api;

import com.example.E_Com.dto.request.RequestCustomerDto;
import com.example.E_Com.service.CustomerService;
import com.example.E_Com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class customerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerDto requestCustomerDto){     // save data -> http://localhost:8001/api/v1/customers [Post]
        customerService.create(requestCustomerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "customer was created!...", requestCustomerDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id, @RequestBody RequestCustomerDto requestCustomerDto){     // update all data -> http://localhost:8001/api/v1/customers [Put]
        customerService.update(id, requestCustomerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer was Updated!...", null),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id){     // delete data -> http://localhost:8001/api/v1/customers [Delete]
        customerService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Customer was Deleted!...", null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){    // find data -> http://localhost:8001/api/v1/customers [Get]
        return new ResponseEntity<>(
                new StandardResponse(200, "customer was founded!...",customerService.getById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){     // find all -> http://localhost:8001/api/v1/customers/list [Get]
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer List...",customerService.getAll(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
