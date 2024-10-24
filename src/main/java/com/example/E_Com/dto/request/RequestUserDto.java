package com.example.E_com.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUserDto {
    private String email;
    private String displayName;
    private String password;
}
