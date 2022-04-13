package careerservice.courseservicegateway;

import courseservice.api.EnrollToCourseCommandMessage;
import courseservice.api.EnrollToCourseReplyMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class Gateway {

    private KafkaTemplate<String, EnrollToCourseCommandMessage> kafkaTemplate;

    private ApplicationEventPublisher publisher;

    public void enroll(EnrollToCourseCommandMessage command) {
        kafkaTemplate.send("courseservice-commands-topic", command);
    }

    @KafkaListener(topics = "courseservice-replies-topic", groupId = "careerservice-group")
    public void handleReply(EnrollToCourseReplyMessage reply) {
        log.debug("Handle reply: {}", reply);
        publisher.publishEvent(reply);
    }


}
