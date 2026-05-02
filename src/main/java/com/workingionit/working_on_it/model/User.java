package com.workingionit.working_on_it.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Special characters are not allowed")
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Special characters are not allowed")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Please enter a valid email format")
    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "City cannot be empty")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String localization;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}