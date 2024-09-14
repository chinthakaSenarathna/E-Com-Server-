package com.example.E_Com.dto.response.paginate;

import com.example.E_Com.dto.response.ResponseCustomerDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPaginateDto {
    private long count;
    private List<ResponseCustomerDto> dataList;
}
