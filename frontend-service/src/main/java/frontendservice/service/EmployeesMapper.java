package frontendservice.service;

import frontendservice.careergateway.GatewayRole;
import frontendservice.dto.CreateEmployeeCommand;
import frontendservice.dto.RoleDto;
import frontendservice.employeegateway.GatewayCreateEmployeeCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesMapper {

    List<RoleDto> toDtos(List<GatewayRole> role);

    GatewayCreateEmployeeCommand toCommand(CreateEmployeeCommand command);
}
