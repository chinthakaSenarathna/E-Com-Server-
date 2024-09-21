package com.example.E_com.api;

import com.example.E_com.dto.request.RequestProductImageDto;
import com.example.E_com.service.ProductImageService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/product-images")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @PostMapping("/{id}")
    public ResponseEntity<StandardResponse> create(@RequestBody RequestProductImageDto requestProductImageDto, @PathVariable String id) throws SQLException, IOException {
        productImageService.create(requestProductImageDto,id);
        return new ResponseEntity<>(
                new StandardResponse(201, "Product Image was Created...", null),
                HttpStatus.CREATED
        );
    }
}
