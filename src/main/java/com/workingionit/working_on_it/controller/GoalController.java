package com.workingionit.working_on_it.controller;

import com.workingionit.working_on_it.dto.GoalRequestDTO;
import com.workingionit.working_on_it.dto.GoalResponseDTO;
import com.workingionit.working_on_it.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalResponseDTO> createGoal(
            @Valid @RequestBody GoalRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        GoalResponseDTO response = goalService.createGoal(dto, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(goalService.getGoalsByUser(userId));
    }

    @GetMapping("/city/{localization}")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByCity(@PathVariable String localization) {
        return ResponseEntity.ok(goalService.getGoalsByCity(localization));
    }

    @PatchMapping("/{goalId}/complete")
    public ResponseEntity<GoalResponseDTO> completeGoal(
            @PathVariable Integer goalId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(goalService.completeGoal(goalId, userDetails.getUsername()));
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Integer goalId,
            @AuthenticationPrincipal UserDetails userDetails) {
        goalService.deleteGoal(goalId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}