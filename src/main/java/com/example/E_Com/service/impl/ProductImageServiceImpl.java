package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestProductImageDto;
import com.example.E_com.dto.response.ResponseProductImageDto;
import com.example.E_com.entity.Product;
import com.example.E_com.entity.ProductImage;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.ProductImageRepository;
import com.example.E_com.repo.ProductRepository;
import com.example.E_com.service.FileService;
import com.example.E_com.service.ProductImageService;
import com.example.E_com.util.CommonFileSavedBinaryDataDto;
import com.example.E_com.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Value("${aws.bucket_name}")
    private String bucketName;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final FileDataExtractor fileDataExtractor;

    @Override
    public void create(RequestProductImageDto requestProductImageDto, String productId) throws SQLException, IOException {
        CommonFileSavedBinaryDataDto resource = null;

        try{
            Optional<Product> selectedProduct = productRepository.findById(productId);

            if(selectedProduct.isEmpty()){
                throw new EntryNotFoundException("Product was Not Exist...");
            }

            resource = fileService.create(requestProductImageDto.getImage(), "e-com/product_images/", bucketName);

            ProductImage productImage = ProductImage.builder()
                    .propertyId(UUID.randomUUID().toString())
                    .directory(resource.getDirectory().getBytes())
                    .resourceUrl(fileDataExtractor.blobToByteArray(resource.getResourceUrl()))
                    .hash(fileDataExtractor.blobToByteArray(resource.getHash()))
                    .fileNme(fileDataExtractor.blobToByteArray(resource.getFileName()))
                    .product(selectedProduct.get())
                    .build();

            productImageRepository.save(productImage);
        } catch (Exception e){
            fileService.delete(fileDataExtractor.extractActualFileName(
                    new InputStreamReader(resource.getFileName().getBinaryStream())
            ),resource.getDirectory(),bucketName);

            throw new RuntimeException("Error while processing product image");
        }

    }

    @Override
    public ResponseProductImageDto getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
