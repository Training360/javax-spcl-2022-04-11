package careerservice.model;

import careerservice.dto.CreateRoleCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public static Role createRole(CreateRoleCommand command) {
        return new Role(command.getName());
    }

}
