package com.workingionit.working_on_it.repository;

import com.workingionit.working_on_it.model.Goal;
import com.workingionit.working_on_it.model.GoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    List<Goal> findByUserId(Integer userId);
    List<Goal> findByUserLocalization(String localization);
    List<Goal> findByUserIdAndStatus(Integer userId, GoalStatus status);
}
