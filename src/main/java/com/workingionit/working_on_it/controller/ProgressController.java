package com.workingionit.working_on_it.controller;

import com.workingionit.working_on_it.dto.ProgressRequestDTO;
import com.workingionit.working_on_it.dto.ProgressResponseDTO;
import com.workingionit.working_on_it.service.ProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/goal/{goalId}")
    public ResponseEntity<ProgressResponseDTO> createProgress(
            @Valid @RequestBody ProgressRequestDTO dto,
            @PathVariable Integer goalId,
            @AuthenticationPrincipal UserDetails userDetails) {
        ProgressResponseDTO response = progressService.createProgress(dto, goalId, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/goal/{goalId}")
    public ResponseEntity<List<ProgressResponseDTO>> getProgressByGoal(@PathVariable Integer goalId) {
        return ResponseEntity.ok(progressService.getProgressByGoal(goalId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressResponseDTO>> getProgressByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(progressService.getProgressByUser(userId));
    }

    @GetMapping("/city/{localization}")
    public ResponseEntity<List<ProgressResponseDTO>> getProgressByCity(@PathVariable String localization) {
        return ResponseEntity.ok(progressService.getProgressByCity(localization));
    }

    @DeleteMapping("/{progressId}")
    public ResponseEntity<Void> deleteProgress(
            @PathVariable Integer progressId,
            @AuthenticationPrincipal UserDetails userDetails) {
        progressService.deleteProgress(progressId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}