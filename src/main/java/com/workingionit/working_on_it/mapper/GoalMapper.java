package com.workingionit.working_on_it.mapper;

import com.workingionit.working_on_it.dto.GoalRequestDTO;
import com.workingionit.working_on_it.dto.GoalResponseDTO;
import com.workingionit.working_on_it.model.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "concludedAt", ignore = true)
    Goal toEntity(GoalRequestDTO dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    GoalResponseDTO toResponseDTO(Goal goal);
}