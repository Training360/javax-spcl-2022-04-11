package employeeservice.service;

import employeeservice.dto.CreateEmployeeRequest;
import employeeservice.dto.EmployeeDto;
import employeeservice.dto.UpdateEmployeeRequest;
import employeeservice.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    public EmployeeDto createEmployee(CreateEmployeeRequest request) {
        var employee = new Employee(request.getName(), request.getRoleId());
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    public EmployeeDto findEmployeeById(long id) {
        var employeeDto = employeeMapper.toDto(employeeRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));
        return employeeDto;
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeRequest request) {
        var employeeToModify = employeeRepository.getById(id);
        employeeToModify.setName(request.getName());
        employeeToModify.setRoleId(request.getRoleId());
        return employeeMapper.toDto(employeeToModify);
    }

    public void deleteEmployee(long id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
