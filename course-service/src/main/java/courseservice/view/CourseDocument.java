package courseservice.view;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "courses")
public class CourseDocument {

    @Id
    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private List<Long> skills;
}
