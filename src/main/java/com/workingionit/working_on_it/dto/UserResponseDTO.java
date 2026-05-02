package com.workingionit.working_on_it.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String localization;
    private LocalDateTime createdAt;
}
