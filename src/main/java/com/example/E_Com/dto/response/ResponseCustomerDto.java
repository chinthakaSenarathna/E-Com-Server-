package com.example.E_com.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustomerDto {
    private String propertyId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private boolean isActive;
}
