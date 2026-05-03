package com.workingionit.working_on_it.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProgressResponseDTO {

    private Integer id;
    private String text;
    private String picture;
    private String video;
    private LocalDateTime createdAt;
    private Integer goalId;
    private String goalObjective;
    private Integer userId;
    private String userName;
}