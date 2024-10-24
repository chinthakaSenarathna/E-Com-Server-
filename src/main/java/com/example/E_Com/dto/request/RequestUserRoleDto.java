package com.example.E_com.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUserRoleDto {
    private String roleName;
    private String roleDescription;
}
