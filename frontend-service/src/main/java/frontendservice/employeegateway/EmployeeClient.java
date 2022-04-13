package frontendservice.employeegateway;

import frontendservice.dto.CreateEmployeeCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "employee", url="${frontend-service.employees-service-url}")
public interface EmployeeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/employees")
    List<GatewayEmployee> listEmployees();

    @RequestMapping(method = RequestMethod.POST, value = "/api/employees", consumes = "application/json")
    GatewayEmployee createEmployee(GatewayCreateEmployeeCommand command);
}
