package employeeservice.controller;

import employeeservice.dto.CreateEmployeeRequest;
import employeeservice.dto.EmployeeDto;
import employeeservice.dto.UpdateEmployeeRequest;
import employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    @SneakyThrows
    public List<EmployeeDto> employees() {

//        if (new Random().nextInt(3) != 0) {
//            Thread.sleep(5000);
//        }

        return employeeService.listEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping // nem idempotens
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "creates an employee")
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @PutMapping("/{id}") // idempotens
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

}
