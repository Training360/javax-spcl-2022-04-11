package frontendservice.careergateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "career", url = "${frontend-service.career-service-url}")
public interface CareerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/roles")
    List<Role> listRoles();
}
