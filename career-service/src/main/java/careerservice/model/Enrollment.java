package careerservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    private long courseId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private Enrollment() {

    }

    public static Enrollment enrollToCourse(EnrollCommand command) {
        var enrollment = new Enrollment();
        enrollment.employeeId = command.getEmployeeId();
        enrollment.courseId = command.getCourseId();
        enrollment.status = EnrollmentStatus.INTENTIONED;
        return enrollment;
    }

    public void registered() {
        status = EnrollmentStatus.REGISTERED;
    }

    public void cancel() {
        status = EnrollmentStatus.CANCELLED;
    }
}
