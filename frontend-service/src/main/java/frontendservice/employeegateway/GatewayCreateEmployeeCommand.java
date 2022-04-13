package frontendservice.employeegateway;

import lombok.Data;

@Data
public class GatewayCreateEmployeeCommand {

    private String name;

    private long roleId;
}
