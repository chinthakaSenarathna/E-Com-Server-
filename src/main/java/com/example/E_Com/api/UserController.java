package com.example.E_com.api;

import com.example.E_com.dto.request.RequestUserDto;
import com.example.E_com.service.UserService;
import com.example.E_com.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/visitor/signup")
    public ResponseEntity<StandardResponse> create(@RequestBody RequestUserDto requestUserDto){
        userService.create(requestUserDto, "USER");
        return new ResponseEntity<>(
                new StandardResponse(201, "User was created", null),
                HttpStatus.CREATED
        );
    }
}
