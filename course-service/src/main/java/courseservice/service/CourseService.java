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

    private ApplicationEventPublisher publisher;

    public CourseView createCourse(CreateCourseCommand command) {
        var response = Course.createCourse(command);
        courseRepository.save(response.getCourse());
        response.getEvent().setId(response.getCourse().getId());
        publisher.publishEvent(response.getEvent());
        return courseMapper.toView(response.getCourse());
    }

    @Transactional
    public CourseView enroll(EnrollCommand command) {
        var course = courseRepository.findById(command.getCourseId())
                .orElseThrow(() -> new NotFoundException(String.format("Course not found: %d", command.getCourseId())));
        course.enroll(command.getEmployeeId());
        return courseMapper.toView(course);
    }

    public List<CourseView> findAllCourseViews() {
        return courseRepository.findAllViews();
    }

    @Transactional(readOnly = true)
    public CourseDetailsView findCourseById(long id) {
        return courseMapper.toDetailsView(courseRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Course not found: %d", id))));
    }
}
