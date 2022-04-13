package careerservice.service;

import careerservice.dto.CreateRoleCommand;
import careerservice.dto.RoleView;
import careerservice.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    private RoleMapper roleMapper;

    public RoleView createRole(CreateRoleCommand command) {
        var role = Role.createRole(command);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public List<RoleView> findAllRoles() {
        return roleRepository.findRoleViewBy();
    }

    public RoleView findRoleById(long id) {
        return roleRepository.findRoleViewById(id)
                        .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
    }

}
