package com.workingionit.working_on_it.service;

import com.workingionit.working_on_it.dto.GoalRequestDTO;
import com.workingionit.working_on_it.dto.GoalResponseDTO;
import java.util.List;

public interface GoalService {
    GoalResponseDTO createGoal(GoalRequestDTO dto, String email);
    List<GoalResponseDTO> getAllGoals();
    List<GoalResponseDTO> getGoalsByUser(Integer userId);
    GoalResponseDTO completeGoal(Integer goalId, String email);
    void deleteGoal(Integer goalId, String email);
}