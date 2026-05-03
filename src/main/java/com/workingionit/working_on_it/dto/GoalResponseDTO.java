package com.workingionit.working_on_it.dto;

import com.workingionit.working_on_it.model.GoalStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GoalResponseDTO {

    private Integer id;
    private String objective;
    private GoalStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime concludedAt;
    private Integer userId;
    private String userName;
}