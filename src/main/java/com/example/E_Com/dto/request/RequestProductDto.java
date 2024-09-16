package com.example.E_com.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductDto {
    private long qty;
    private double unitPrice;
    private String description;
}
