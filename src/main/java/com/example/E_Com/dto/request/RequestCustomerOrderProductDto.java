package com.example.E_com.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCustomerOrderProductDto {
    private String propertyId;
    private int qty;
}
