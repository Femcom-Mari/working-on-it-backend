package com.workingionit.working_on_it.repository;

import com.workingionit.working_on_it.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer> {

    List<Progress> findByGoalId(Integer goalId);
    List<Progress> findByUserId(Integer userId);
    List<Progress> findByGoalUserLocalization(String localization);
}