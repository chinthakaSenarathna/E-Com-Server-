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
    public String update(@PathVariable String id, @RequestBody RequestCustomerDto requestCustomerDto){     // update all data -> http://localhost:8001/api/v1/customers [Put]
        System.out.println(requestCustomerDto.getName());
        return "update()";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){     // delete data -> http://localhost:8001/api/v1/customers [Delete]
        return "delete()";
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){    // find data -> http://localhost:8001/api/v1/customers [Get]
        return customerService.getById(id);
    }

    @GetMapping("/list")
    public String getAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){     // find all -> http://localhost:8001/api/v1/customers/list [Get]
        return "getAll()";
    }
}
