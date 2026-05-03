package com.workingionit.working_on_it.service.impl;

import com.workingionit.working_on_it.dto.GoalRequestDTO;
import com.workingionit.working_on_it.dto.GoalResponseDTO;
import com.workingionit.working_on_it.mapper.GoalMapper;
import com.workingionit.working_on_it.model.Goal;
import com.workingionit.working_on_it.model.GoalStatus;
import com.workingionit.working_on_it.model.User;
import com.workingionit.working_on_it.repository.GoalRepository;
import com.workingionit.working_on_it.repository.UserRepository;
import com.workingionit.working_on_it.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final GoalMapper goalMapper;

    @Override
    public GoalResponseDTO createGoal(GoalRequestDTO dto, String email) {
        log.info("Creating goal for user: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = goalMapper.toEntity(dto);
        goal.setUser(user);
        goal.setStatus(GoalStatus.ACTIVE);

        Goal savedGoal = goalRepository.save(goal);
        log.info("Goal created with id: {}", savedGoal.getId());

        return goalMapper.toResponseDTO(savedGoal);
    }

    @Override
    public List<GoalResponseDTO> getGoalsByUser(Integer userId) {
        log.info("Getting goals for user id: {}", userId);
        return goalRepository.findByUserId(userId)
                .stream()
                .map(goalMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<GoalResponseDTO> getGoalsByCity(String localization) {
        log.info("Getting goals for city: {}", localization);
        return goalRepository.findByUserLocalization(localization)
                .stream()
                .map(goalMapper::toResponseDTO)
                .toList();
    }

    @Override
    public GoalResponseDTO completeGoal(Integer goalId, String email) {
        log.info("Completing goal id: {}", goalId);

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        goal.setStatus(GoalStatus.COMPLETED);
        goal.setConcludedAt(LocalDateTime.now());

        return goalMapper.toResponseDTO(goalRepository.save(goal));
    }

    @Override
    public void deleteGoal(Integer goalId, String email) {
        log.info("Deleting goal id: {}", goalId);

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        goalRepository.delete(goal);
        log.info("Goal deleted successfully");
    }
}