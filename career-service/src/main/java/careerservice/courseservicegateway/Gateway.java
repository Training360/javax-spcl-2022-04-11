package careerservice.courseservicegateway;

import courseservice.api.EnrollToCourseCommandMessage;

public interface Gateway {

    void enroll(EnrollToCourseCommandMessage command);
}
