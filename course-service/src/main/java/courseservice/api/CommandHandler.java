package courseservice.api;

import courseservice.service.CourseService;
import courseservice.service.EnrollCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommandHandler {

    private CourseService courseService;

    private KafkaTemplate<String, EnrollToCourseReplyMessage> kafkaTemplate;

    @KafkaListener(topics = "courseservice-commands-topic", groupId = "courseservice-group")
    public void handleCommand(EnrollToCourseCommandMessage message) {
        var command = new EnrollCommand();
        command.setCourseId(message.getCourseId());
        command.setEmployeeId(message.getEmployeeId());
        try {
            courseService.enroll(command);
            kafkaTemplate.send("courseservice-replies-topic", EnrollToCourseReplyMessage.success(message.getCourseId(), message.getEmployeeId()));
        } catch (Exception e) {
            kafkaTemplate.send("courseservice-replies-topic", EnrollToCourseReplyMessage.error(message.getCourseId(), message.getEmployeeId()));
        }
    }


}
