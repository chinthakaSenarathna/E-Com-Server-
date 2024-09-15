package com.example.E_com.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCustomerDto {
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private boolean isActive;
}
