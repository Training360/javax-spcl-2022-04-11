package courseservice.service;

import courseservice.dto.CourseDetailsView;
import courseservice.dto.CourseView;
import courseservice.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseView toView(Course course);

    CourseDetailsView toDetailsView(Course course);
}
