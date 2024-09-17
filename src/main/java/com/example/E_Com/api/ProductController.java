package com.example.E_com.api;

import com.example.E_com.dto.request.RequestProductDto;
import com.example.E_com.service.ProductService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestProductDto requestProductDto){
        productService.create(requestProductDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Product was Created!...",null),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id,@RequestBody RequestProductDto requestProductDto){
        productService.update(id,requestProductDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Product was Updated!...",null),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id){
        productService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Product was Deleted!...",null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){;
        return new ResponseEntity<>(
                new StandardResponse(200, "Product was Found!...",productService.getById(id)),
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
                new StandardResponse(200, "Product List!...",productService.getAll(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
