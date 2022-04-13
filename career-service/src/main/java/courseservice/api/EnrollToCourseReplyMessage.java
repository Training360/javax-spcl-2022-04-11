package courseservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollToCourseReplyMessage {

    private long courseId;

    private long employeeId;

    private boolean success;

    public static EnrollToCourseReplyMessage success(long courseId, long employeeId) {
        return new EnrollToCourseReplyMessage(courseId, employeeId, true);
    }

    public static EnrollToCourseReplyMessage error(long courseId, long employeeId) {
        return new EnrollToCourseReplyMessage(courseId, employeeId, false);
    }
}
