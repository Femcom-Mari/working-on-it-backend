package com.workingionit.working_on_it.mapper;


import com.workingionit.working_on_it.dto.RegisterRequestDTO;
import com.workingionit.working_on_it.dto.UserResponseDTO;
import com.workingionit.working_on_it.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(RegisterRequestDTO dto);

    UserResponseDTO toResponseDTO(User user);
}