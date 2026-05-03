package com.workingionit.working_on_it.service;

import com.workingionit.working_on_it.dto.ProgressRequestDTO;
import com.workingionit.working_on_it.dto.ProgressResponseDTO;
import java.util.List;

public interface ProgressService {
    ProgressResponseDTO createProgress(ProgressRequestDTO dto, Integer goalId, String email);
    List<ProgressResponseDTO> getProgressByGoal(Integer goalId);
    List<ProgressResponseDTO> getProgressByUser(Integer userId);
    List<ProgressResponseDTO> getProgressByCity(String localization);
    void deleteProgress(Integer progressId, String email);
}