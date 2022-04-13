package careerservice.saga;

import careerservice.courseservicegateway.Gateway;
import careerservice.dto.EnrollmentView;
import careerservice.model.EnrollCommand;
import careerservice.service.EnrollmentService;
import courseservice.api.EnrollToCourseCommandMessage;
import courseservice.api.EnrollToCourseReplyMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollSaga {

    private EnrollmentService enrollmentService;

    private Gateway gateway;

    public EnrollmentView enroll(EnrollCommand command) {
        var view = enrollmentService.enrollToCourse(command);
        gateway.enroll(
                new EnrollToCourseCommandMessage(command.getCourseId(), command.getEmployeeId()));
        return view;
    }

    @EventListener
    public void registered(EnrollToCourseReplyMessage reply) {
        if (reply.isSuccess()) {
            enrollmentService.registered(reply.getCourseId(), reply.getEmployeeId());
        }
        else {
            // Kompenzáló tranzakció
            enrollmentService.cancel(reply.getCourseId(), reply.getEmployeeId());
        }
    }
}
