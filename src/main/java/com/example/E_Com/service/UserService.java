package com.example.E_com.service;

import com.example.E_com.dto.request.RequestUserDto;

public interface UserService {
    public void create(RequestUserDto requestUserDto,String roleType);
}
