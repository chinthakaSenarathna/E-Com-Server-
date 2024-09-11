package com.example.E_Com.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class customerController {
    @PostMapping()
    public String create(){     // save data -> http://localhost:8001/api/v1/customers [Post]
        return "create()";
    }

    @PutMapping()
    public String update(){     // update all data -> http://localhost:8001/api/v1/customers [Put]
        return "update()";
    }

    @GetMapping()
    public String getById(){    // find data -> http://localhost:8001/api/v1/customers [Get]
        return "getById()";
    }

    @GetMapping("/list")
    public String getAll(){     // find all -> http://localhost:8001/api/v1/customers/list [Get]
        return "getAll()";
    }

    @DeleteMapping()
    public String delete(){     // delete data -> http://localhost:8001/api/v1/customers [Delete]
        return "delete()";
    }
}
