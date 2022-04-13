package careerservice.controller;

import careerservice.dto.CreateRoleCommand;
import careerservice.dto.EnrollmentView;
import careerservice.dto.RoleView;
import careerservice.model.EnrollCommand;
import careerservice.model.Enrollment;
import careerservice.saga.EnrollSaga;
import careerservice.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    private EnrollSaga enrollSaga;

    @GetMapping
    public List<EnrollmentView> findAllByEmployee(@RequestParam long employeeId) {
        return enrollmentService.findAllByEmployee(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentView enrollToCourse(@RequestBody EnrollCommand command) {
        return enrollSaga.enroll(command);
    }
}
