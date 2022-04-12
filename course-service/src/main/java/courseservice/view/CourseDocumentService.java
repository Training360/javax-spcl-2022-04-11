package courseservice.view;

import courseservice.dto.CourseCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseDocumentService {

    private CourseDocumentRepository repository;

    private CourseDocumentMapper mapper;

    @EventListener
    public void handleEvent(CourseCreatedEvent event) {
        var document = mapper.toDocument(event);
        repository.save(document);
    }

    public List<CourseDocumentView> findByWord(String word) {
        return mapper.toViews(repository.findByWordUsingCustomQuery(word));
    }
}
