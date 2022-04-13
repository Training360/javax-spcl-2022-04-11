package frontendservice.service;

import frontendservice.careergateway.Role;
import frontendservice.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesMapper {

    List<RoleDto> toDtos(List<Role> role);
}
