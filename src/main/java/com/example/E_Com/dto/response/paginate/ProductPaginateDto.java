package com.example.E_com.dto.response.paginate;

import com.example.E_com.dto.response.ResponseProductDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPaginateDto {
    private long count;
    private List<ResponseProductDto> dataList;
}
