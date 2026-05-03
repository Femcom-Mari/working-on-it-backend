package com.workingionit.working_on_it.mapper;

import com.workingionit.working_on_it.dto.ProgressRequestDTO;
import com.workingionit.working_on_it.dto.ProgressResponseDTO;
import com.workingionit.working_on_it.model.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "goal", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Progress toEntity(ProgressRequestDTO dto);

    @Mapping(target = "goalId", source = "goal.id")
    @Mapping(target = "goalObjective", source = "goal.objective")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    ProgressResponseDTO toResponseDTO(Progress progress);
}