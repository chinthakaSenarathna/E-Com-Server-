package com.example.E_com.dto.response;

import com.example.E_com.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustomerOrderDto {
    private String propertyId;
    private Date createdDate;
    private double totalAmount;
    List<ResponseCustomerOrderProductDto> orderProducts;
    private PaymentType paymentType;
    private String customer;
}
