package com.workingionit.working_on_it.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String localization;
}