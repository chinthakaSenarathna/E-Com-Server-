package com.example.E_Com.api;

import com.example.E_Com.dto.request.RequestCustomerDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class customerController {
    @PostMapping()
    public String create(@RequestBody RequestCustomerDto requestCustomerDto){     // save data -> http://localhost:8001/api/v1/customers [Post]
        System.out.println(requestCustomerDto.getName());
        return "create()";
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
    public String getById(@PathVariable String id){    // find data -> http://localhost:8001/api/v1/customers [Get]
        return "getById()";
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
