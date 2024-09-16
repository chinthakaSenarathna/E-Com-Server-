package com.example.E_com.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {
    private String propertyId;
    private long qty;
    private double unitPrice;
    private String description;
    List<ResponseProductImageDto> productImages;
}
