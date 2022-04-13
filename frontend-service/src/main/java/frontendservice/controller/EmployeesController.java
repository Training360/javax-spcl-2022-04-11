package frontendservice.controller;

import frontendservice.employeesgateway.CreateEmployeeCommand;
import frontendservice.employeesgateway.EmployeeClient;
import frontendservice.inboxgateway.CalendarItemDto;
import frontendservice.service.EmployeesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping("/employees")
    public ModelAndView listEmployees() {
        Map<String, Object> model = new HashMap<>();
        model.put("employees", employeesService.listEmployees());
        model.put("roles", employeesService.listRoles());
        model.put("command", new CreateEmployeeCommand());

        return new ModelAndView("employees", model);
    }

    @PostMapping("/employees")
    public ModelAndView createEmployee(@ModelAttribute CreateEmployeeCommand command) {
        employeesService.createEmployee(command);
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/calendar-items")
    public ModelAndView listInbox(@RequestParam long employeeId) {
        List<CalendarItemDto> calendarItems = employeesService.listInbox(employeeId);
        var model = Map.of("calendarItems", calendarItems);
        return new ModelAndView("calendar-items", model);
    }

}