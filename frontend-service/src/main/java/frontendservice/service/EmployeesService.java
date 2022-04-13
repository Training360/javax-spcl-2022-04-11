package frontendservice.service;

import frontendservice.careergateway.CareerClient;
import frontendservice.careergateway.Role;
import frontendservice.employeesgateway.CreateEmployeeCommand;
import frontendservice.employeesgateway.Employee;
import frontendservice.employeesgateway.EmployeeClient;
import frontendservice.inboxgateway.CalendarItemDto;
import frontendservice.inboxgateway.InboxClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeesMapper employeesMapper;

    public List<RoleDto> listRoles() {
    }

    public List<EmployeeDto> listEmployees() {
    }

    public void createEmployee(CreateEmployeeCommand command) {
        employeeClient.createEmployee(command);
    }

    public List<CalendarItemDto> listInbox(long employeeId) {
        return List.of();
    }
}
