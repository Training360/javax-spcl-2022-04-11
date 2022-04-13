package employeeservice.service;

import employeeservice.dto.EmployeeDto;
import employeeservice.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);

}
