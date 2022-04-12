package courseservice.dto;

import courseservice.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCourseResponse {

    private Course course;

    private CourseCreatedEvent event;

}
