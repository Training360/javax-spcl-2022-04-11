package careerservice.service;

import careerservice.dto.RoleView;
import careerservice.model.Enrollment;
import careerservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {


    List<Enrollment> findAllByEmployeeId(long employeeId);

    Optional<Enrollment> findByCourseIdAndEmployeeId(long courseId, long employeeId);
}
