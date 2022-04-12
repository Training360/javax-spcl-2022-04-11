package courseservice.view;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course-documents")
@AllArgsConstructor
public class CourseDocumentController {

    private CourseDocumentService service;

    @GetMapping
    public List<CourseDocumentView> findAll(@RequestParam String word) {
        return service.findByWord(word);
    }
}
