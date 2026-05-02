package com.workingionit.working_on_it.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Special characters are not allowed")
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Special characters are not allowed")
    private String lastName;

    @Email(message = "Please enter a valid email format")
    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "City cannot be empty")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String localization;
}
