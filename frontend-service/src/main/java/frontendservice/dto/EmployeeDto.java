package frontendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String name;

    private String role;
}
