package com.workingionit.working_on_it.service;

import com.workingionit.working_on_it.dto.LoginRequestDTO;
import com.workingionit.working_on_it.dto.LoginResponseDTO;
import com.workingionit.working_on_it.dto.RegisterRequestDTO;
import com.workingionit.working_on_it.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(RegisterRequestDTO dto);
    LoginResponseDTO loginUser(LoginRequestDTO dto);
}