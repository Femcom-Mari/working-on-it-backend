package com.workingionit.working_on_it.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GoalRequestDTO {

    @NotBlank(message = "Objective cannot be empty")
    @Size(max = 100, message = "Objective cannot exceed 100 characters")
    private String objective;
}