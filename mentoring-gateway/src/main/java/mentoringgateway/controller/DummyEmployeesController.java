package mentoringgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/dummy-employees")
public class DummyEmployeesController {

    @GetMapping
    public Flux<EmployeeDto> dummyEmployees() {
        return Flux.fromIterable(List.of(new EmployeeDto(1L, "Dummy Joe", 1)));
    }
}
