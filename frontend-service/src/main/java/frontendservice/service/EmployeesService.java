package frontendservice.service;

import frontendservice.careergateway.CareerClient;
import frontendservice.careergateway.GatewayRole;
import frontendservice.dto.CreateEmployeeCommand;
import frontendservice.dto.EmployeeDto;
import frontendservice.dto.RoleDto;
import frontendservice.employeegateway.EmployeeClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeesMapper employeesMapper;

    private CareerClient careerClient;

    private EmployeeClient employeeClient;

    public List<RoleDto> listRoles() {
        return employeesMapper.toDtos(careerClient.listRoles());
    }

    public List<EmployeeDto> listEmployees() {
        // API composition
        var roles = careerClient.listRoles().stream()
                .collect(Collectors.toMap(GatewayRole::getId, GatewayRole::getName));


        return employeeClient.listEmployees().stream().map(e -> new EmployeeDto(e.getId(), e.getName(), roles.get(e.getRoleId())))
                .toList();
    }

    public void createEmployee(CreateEmployeeCommand command) {
        employeeClient.createEmployee(employeesMapper.toCommand(command));
    }

}
