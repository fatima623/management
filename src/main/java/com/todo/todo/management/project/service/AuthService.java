package com.todo.todo.management.project.service;

import com.todo.todo.management.project.dto.LoginDto;
import com.todo.todo.management.project.dto.RegisterDto;

public interface AuthService {
    String Register(RegisterDto registerDto);
    String Login(LoginDto loginDto);
}
