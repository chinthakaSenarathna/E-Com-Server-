package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestProductDto;
import com.example.E_com.dto.response.ResponseProductDto;
import com.example.E_com.dto.response.paginate.ProductPaginateDto;
import com.example.E_com.service.ProductService;

public class ProductServiceImpl implements ProductService {
    @Override
    public void create(RequestProductDto requestProductDto) {

    }

    @Override
    public ResponseProductDto getById(String id) {
        return null;
    }

    @Override
    public void update(String id, ResponseProductDto responseProductDto) {

    }

    @Override
    public ProductPaginateDto getAll(String searchText, int page, int size) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
