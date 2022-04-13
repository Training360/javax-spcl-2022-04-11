package careerservice.service;

import careerservice.dto.RoleView;
import careerservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select new careerservice.dto.RoleView(r.id, r.name) from Role r")
    List<RoleView> findRoleViewBy();

    @Query(value = "select new careerservice.dto.RoleView(r.id, r.name) from Role r where r.id = :id")
    Optional<RoleView> findRoleViewById(long id);

}
