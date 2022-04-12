package courseservice.service;

import courseservice.dto.CourseDetailsView;
import courseservice.dto.CourseView;
import courseservice.dto.CreateCourseCommand;
import courseservice.model.Course;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    public CourseView createCourse(CreateCourseCommand command) {
        var course = Course.createCourse(command);
        courseRepository.save(course);
        return courseMapper.toView(course);
    }

    @Transactional
    public CourseView enroll(EnrollCommand command) {
        var course = courseRepository.findById(command.getCourseId())
                .orElseThrow(() -> new NotFoundException(String.format("Course not found: %d", command.getCourseId())));
        course.enroll(command.getEmployeeId());
        return courseMapper.toView(course);
    }

}
