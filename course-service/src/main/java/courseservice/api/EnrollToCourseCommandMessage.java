package courseservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollToCourseCommandMessage {

    private long courseId;

    private long employeeId;
}
