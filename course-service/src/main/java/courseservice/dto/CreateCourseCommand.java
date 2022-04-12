package courseservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateCourseCommand {

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    List<Long> skills;

}
