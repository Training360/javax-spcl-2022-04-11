package courseservice.dto;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Lob;
import java.util.List;

@Data
public class CourseDetailsView {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    List<Long> skills;

    List<Long> enrolledEmployees;

    List<Long> completedEmployees;
}
