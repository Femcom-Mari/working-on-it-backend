package com.workingionit.working_on_it.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProgressRequestDTO {

    @Size(max = 500, message = "Text cannot exceed 500 characters")
    private String text;

    private String picture;
    private String video;
}