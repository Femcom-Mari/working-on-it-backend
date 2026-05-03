package com.workingionit.working_on_it.service.impl;

import com.workingionit.working_on_it.dto.LoginRequestDTO;
import com.workingionit.working_on_it.dto.LoginResponseDTO;
import com.workingionit.working_on_it.dto.RegisterRequestDTO;
import com.workingionit.working_on_it.dto.UserResponseDTO;
import com.workingionit.working_on_it.mapper.UserMapper;
import com.workingionit.working_on_it.model.User;
import com.workingionit.working_on_it.repository.UserRepository;
import com.workingionit.working_on_it.security.JwtService;
import com.workingionit.working_on_it.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponseDTO registerUser(RegisterRequestDTO dto) {
        log.info("Registering new user with email: {}", dto.getEmail());

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);
        log.info("User registered successfully with id: {}", savedUser.getId());

        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO dto) {
        log.info("Login attempt for email: {}", dto.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        log.info("Login successful for email: {}", dto.getEmail());

        return new LoginResponseDTO(
                token,
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getLocalization()
        );
    }
}