package courseservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
public class CourseCreatedEvent {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private List<Long> skills;
}
