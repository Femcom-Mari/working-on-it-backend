package com.workingionit.working_on_it.service.impl;

import com.workingionit.working_on_it.dto.ProgressRequestDTO;
import com.workingionit.working_on_it.dto.ProgressResponseDTO;
import com.workingionit.working_on_it.mapper.ProgressMapper;
import com.workingionit.working_on_it.model.Goal;
import com.workingionit.working_on_it.model.Progress;
import com.workingionit.working_on_it.model.User;
import com.workingionit.working_on_it.repository.GoalRepository;
import com.workingionit.working_on_it.repository.ProgressRepository;
import com.workingionit.working_on_it.repository.UserRepository;
import com.workingionit.working_on_it.service.ProgressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final ProgressMapper progressMapper;

    @Override
    public ProgressResponseDTO createProgress(ProgressRequestDTO dto, Integer goalId, String email) {
        log.info("Creating progress for goal id: {} by user: {}", goalId, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        Progress progress = progressMapper.toEntity(dto);
        progress.setUser(user);
        progress.setGoal(goal);

        Progress savedProgress = progressRepository.save(progress);
        log.info("Progress created with id: {}", savedProgress.getId());

        return progressMapper.toResponseDTO(savedProgress);
    }

    @Override
    public List<ProgressResponseDTO> getProgressByGoal(Integer goalId) {
        log.info("Getting progress for goal id: {}", goalId);
        return progressRepository.findByGoalId(goalId)
                .stream()
                .map(progressMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProgressResponseDTO> getProgressByUser(Integer userId) {
        log.info("Getting progress for user id: {}", userId);
        return progressRepository.findByUserId(userId)
                .stream()
                .map(progressMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProgressResponseDTO> getProgressByCity(String localization) {
        log.info("Getting progress for city: {}", localization);
        return progressRepository.findByGoalUserLocalization(localization)
                .stream()
                .map(progressMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteProgress(Integer progressId, String email) {
        log.info("Deleting progress id: {}", progressId);

        Progress progress = progressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        if (!progress.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        progressRepository.delete(progress);
        log.info("Progress deleted successfully");
    }
}