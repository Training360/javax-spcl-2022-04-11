package courseservice.model;

import courseservice.dto.CreateCourseCommand;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String syllabus;

    @Column(name = "enrolled_limit")
    private int limit;

    @ElementCollection
    private List<Long> skills;

    @ElementCollection
    private List<Long> enrolledEmployees;

    @ElementCollection
    private List<Long> completedEmployees;

    public static Course createCourse(CreateCourseCommand command) {
        Course course = new Course();
        course.setName(command.getName());
        course.setDescription(command.getDescription());
        course.setSyllabus(command.getSyllabus());
        course.setLimit(command.getLimit());
        course.setSkills(command.getSkills());
        return course;
    }

    public void enroll(long employeeId) {
        if (enrolledEmployees == null) {
            enrolledEmployees = new ArrayList<>();
        }
        if (enrolledEmployees.size() < limit) {
            enrolledEmployees.add(employeeId);
        }
        else {
            throw new IllegalArgumentException(String.format("Too many enrollment, no place for {}", employeeId));
        }
    }

    public void cancelEnrollment(long employeeId) {
        if (enrolledEmployees.contains(employeeId)) {
            enrolledEmployees.remove(employeeId);
        }
        else {
            throw new IllegalArgumentException(String.format("Not enrolled: {}", employeeId));
        }
    }

    public void complete(long employeeId) {
        if (completedEmployees == null) {
            completedEmployees = new ArrayList<>();
        }
        if (enrolledEmployees.contains(employeeId)) {
            completedEmployees.add(employeeId);
        }
        else {
            throw new IllegalArgumentException(String.format("Not enrolled: {}", employeeId));
        }
    }



}
