package com.example.E_com.service;

import com.example.E_com.dto.request.RequestProductImageDto;
import com.example.E_com.dto.response.ResponseProductImageDto;

import java.io.IOException;
import java.sql.SQLException;

public interface ProductImageService {
    public void create(RequestProductImageDto requestProductImageDto,String productId) throws SQLException, IOException;
    public ResponseProductImageDto getById(String id);
    public void delete(String id);
}
