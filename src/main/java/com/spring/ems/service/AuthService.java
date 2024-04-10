package com.spring.ems.service;

import com.spring.ems.dto.LoginDto;
import com.spring.ems.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
