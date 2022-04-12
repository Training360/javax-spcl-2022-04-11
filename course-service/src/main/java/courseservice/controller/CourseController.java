package courseservice.controller;

import courseservice.dto.CourseDetailsView;
import courseservice.dto.CreateCourseCommand;
import courseservice.dto.CourseView;
import courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    @GetMapping
    public List<CourseView> findAll() {
        return courseService.findAllCourseViews();
    }

    @GetMapping("/{id}")
    public CourseDetailsView findById(@PathVariable("id") long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping // nem idempotens
    @ResponseStatus(HttpStatus.CREATED)
    public CourseView createDocument(@RequestBody CreateCourseCommand command) {
        return courseService.createCourse(command);
    }


}
