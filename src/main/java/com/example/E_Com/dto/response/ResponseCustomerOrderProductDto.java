package com.example.E_com.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustomerOrderProductDto {
    private String propertyId;
    private int qty;
}
