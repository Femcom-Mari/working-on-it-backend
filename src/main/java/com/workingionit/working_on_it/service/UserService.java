package com.workingionit.working_on_it.service;

import com.workingionit.working_on_it.dto.RegisterRequestDTO;
import com.workingionit.working_on_it.dto.UserResponseDTO;
import com.workingionit.working_on_it.mapper.UserMapper;
import com.workingionit.working_on_it.model.User;
import com.workingionit.working_on_it.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO registerUser(RegisterRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = userMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }
}