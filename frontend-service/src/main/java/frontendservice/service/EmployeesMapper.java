package frontendservice.service;

import frontendservice.careergateway.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeesMapper {

    RoleDto toDto(Role role);
}
