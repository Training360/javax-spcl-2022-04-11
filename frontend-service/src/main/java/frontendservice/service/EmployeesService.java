package frontendservice.service;

import frontendservice.careergateway.CareerClient;
import frontendservice.careergateway.Role;
import frontendservice.dto.CreateEmployeeCommand;
import frontendservice.dto.EmployeeDto;
import frontendservice.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeesMapper employeesMapper;

    private CareerClient careerClient;

    public List<RoleDto> listRoles() {
        return employeesMapper.toDtos(careerClient.listRoles());
    }

    public List<EmployeeDto> listEmployees() {
        return List.of();
    }

    public void createEmployee(CreateEmployeeCommand command) {

    }

}
