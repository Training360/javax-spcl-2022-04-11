package careerservice.service;

import careerservice.dto.EnrollmentView;
import careerservice.model.Enrollment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentView toView(Enrollment enrollment);

    List<EnrollmentView> toViews(List<Enrollment> enrollments);
}
