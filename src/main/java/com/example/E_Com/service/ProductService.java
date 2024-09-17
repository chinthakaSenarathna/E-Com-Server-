package com.example.E_com.service;

import com.example.E_com.dto.request.RequestProductDto;
import com.example.E_com.dto.response.ResponseProductDto;
import com.example.E_com.dto.response.paginate.ProductPaginateDto;

public interface ProductService {
    public void create(RequestProductDto requestProductDto);
    public ResponseProductDto getById(String id);
    public void update(String id,RequestProductDto requestProductDto);
    public ProductPaginateDto getAll(String searchText, int page, int size);
    public void delete(String id);
}
