package employeeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long roleId;

    public Employee(String name, long roleId) {
        this.name = name;
        this.roleId = roleId;
    }

}
