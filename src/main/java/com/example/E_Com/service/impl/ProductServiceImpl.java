package com.example.E_com.service.impl;

import com.example.E_com.dto.request.RequestProductDto;
import com.example.E_com.dto.response.ResponseProductDto;
import com.example.E_com.dto.response.ResponseProductImageDto;
import com.example.E_com.dto.response.paginate.ProductPaginateDto;
import com.example.E_com.entity.Product;
import com.example.E_com.entity.ProductImage;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.repo.ProductRepository;
import com.example.E_com.service.ProductService;
import com.example.E_com.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FileDataExtractor fileDataExtractor;

    @Override
    public void create(RequestProductDto requestProductDto) {
        Product product = Product.builder()
                .propertyId(UUID.randomUUID().toString())
                .unitPrice(requestProductDto.getUnitPrice())
                .qty(requestProductDto.getQty())
                .description(requestProductDto.getDescription())
                .build();

        productRepository.save(product);
    }

    @Override
    public ResponseProductDto getById(String id) {
        Optional<Product> selectedProduct = productRepository.findById(id);

        if(selectedProduct.isEmpty()){
            throw new EntryNotFoundException("Product is Not Exist...");
        }

        Product product = selectedProduct.get();

        return toResponseProductDto(product);
    }

    @Override
    public void update(String id, RequestProductDto requestProductDto) {
        Optional<Product> selectedProduct = productRepository.findById(id);

        if(selectedProduct.isEmpty()){
            throw new EntryNotFoundException("Product is Not Exist...");
        }

        Product product = selectedProduct.get();

        product.setUnitPrice(requestProductDto.getUnitPrice());
        product.setQty(requestProductDto.getQty());
        product.setDescription(requestProductDto.getDescription());
        product.setPropertyId(id);

        productRepository.save(product);

    }

    @Override
    public ProductPaginateDto getAll(String searchText, int page, int size) {
        return ProductPaginateDto.builder()
                .count(productRepository.countAllWithSearchText(searchText))
                .dataList(productRepository.findAllWithSearchText(searchText, PageRequest.of(page,size))
                        .stream().map(this::toResponseProductDto).toList())
                .build();
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    private ResponseProductDto toResponseProductDto(Product product){
        List<ResponseProductImageDto> productImages = product.getImages().stream().map(
                this::toResponseProductImageDto
        ).toList();

        return ResponseProductDto.builder()
                .propertyId(product.getPropertyId())
                .unitPrice(product.getUnitPrice())
                .qty(product.getQty())
                .description(product.getDescription())
                .productImages(productImages)
                .build();
    }

    private ResponseProductImageDto toResponseProductImageDto(ProductImage productImage){
        return ResponseProductImageDto.builder()
                .propertyId(productImage.getPropertyId())
                .hash(fileDataExtractor.byteArrayToString(productImage.getHash()))
                .directory(fileDataExtractor.byteArrayToString(productImage.getDirectory()))
                .fileNme(fileDataExtractor.byteArrayToString(productImage.getFileNme()))
                .resourceUrl(fileDataExtractor.byteArrayToString(productImage.getResourceUrl()))
                .build();
    }
}
