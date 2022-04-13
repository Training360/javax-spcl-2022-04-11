package careerservice.service;

import careerservice.dto.EnrollmentView;
import careerservice.dto.RoleView;
import careerservice.model.EnrollCommand;
import careerservice.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    private EnrollmentMapper enrollmentMapper;

    public List<EnrollmentView> findAllByEmployee(long employeeId) {
        return enrollmentMapper.toViews(enrollmentRepository.findAllByEmployeeId(employeeId));
    }

    public EnrollmentView enrollToCourse(EnrollCommand command) {
        var enrolled = enrollmentRepository.findByCourseIdAndEmployeeId(command.getCourseId(), command.getEmployeeId()).isPresent();
        if (enrolled) {
            throw new IllegalArgumentException("Already enrolled");
        }
        var enrollment = Enrollment.enrollToCourse(command);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toView(enrollment);
    }

    @Transactional
    public void registered(long courseId, long employeeId) {
        var enrollment = enrollmentRepository.findByCourseIdAndEmployeeId(courseId, employeeId);
        if (enrollment.isPresent()) {
            enrollment.get().registered();
        }
        else {
            log.debug("Not found {}, {}", courseId, employeeId);
        }
    }

    @Transactional
    public void cancel(long courseId, long employeeId) {
        var enrollment = enrollmentRepository.findByCourseIdAndEmployeeId(courseId, employeeId);
        if (enrollment.isPresent()) {
            enrollment.get().cancel();
        }
        else {
            log.debug("Not found {}, {}", courseId, employeeId);
        }
    }
}
