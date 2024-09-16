package com.example.E_com.dto.response.paginate;

import com.example.E_com.dto.response.ResponseCustomerOrderDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderPaginateDto {
    private long count;
    private List<ResponseCustomerOrderDto> dataList;
}
