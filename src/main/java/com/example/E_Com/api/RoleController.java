package com.example.E_com.api;

import com.example.E_com.dto.request.RequestUserRoleDto;
import com.example.E_com.service.UserRoleService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final UserRoleService userRoleService;

    @PostMapping("/")
    public ResponseEntity<StandardResponse> create(@RequestBody RequestUserRoleDto requestUserRoleDto){
        userRoleService.create(requestUserRoleDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Role was created", null),
                HttpStatus.CREATED
        );
    }
}
