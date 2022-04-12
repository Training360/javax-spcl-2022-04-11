package courseservice.model;

import courseservice.dto.CourseCreatedEvent;
import courseservice.dto.CreateCourseCommand;
import courseservice.dto.CreateCourseResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Aggregate root
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

    public static CreateCourseResponse createCourse(CreateCourseCommand command) {
        var course = createCourseByCommand(command);
        var event = createEventByCourse(course);
        return new CreateCourseResponse(course, event);
    }

    private static CourseCreatedEvent createEventByCourse(Course course) {
        return CourseCreatedEvent.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .syllabus(course.getSyllabus())
                .skills(course.getSkills()).build();
    }

    private static Course createCourseByCommand(CreateCourseCommand command) {
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
