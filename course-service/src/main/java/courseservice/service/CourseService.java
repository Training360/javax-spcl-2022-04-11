package courseservice.service;

import courseservice.messages.EnrollCourseCommand;
import courseservice.messages.EnrollCourseReply;
import courseservice.dto.CourseDetailsView;
import courseservice.dto.CourseView;
import courseservice.dto.CreateCourseCommand;
import courseservice.model.Course;
import employeeservice.messages.EmployeeHasDeletedEvent;
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

}
