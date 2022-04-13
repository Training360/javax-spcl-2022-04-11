package courseservice.api;

import courseservice.dto.CourseCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventPublisher {

    private KafkaTemplate<String, CourseCreatedEvent> kafkaTemplate;

    @EventListener
    public void handleEvent(CourseCreatedEvent event) {
        kafkaTemplate.send("courseservice-events-topic", event);
    }
}
