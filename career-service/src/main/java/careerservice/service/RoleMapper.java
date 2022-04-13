package careerservice.service;

import careerservice.dto.RoleView;
import careerservice.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleView toDto(Role role);

}
