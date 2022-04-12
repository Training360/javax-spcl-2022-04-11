package courseservice.view;

import courseservice.dto.CourseCreatedEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseDocumentMapper {

    CourseDocument toDocument(CourseCreatedEvent event);

    List<CourseDocumentView> toViews(List<CourseDocument> byWordUsingCustomQuery);
}
