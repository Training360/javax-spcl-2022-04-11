package courseservice.service;

import courseservice.dto.CourseView;
import courseservice.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new courseservice.dto.CourseView(c.id, c.name, c.limit) from Course c")
    List<CourseView> findAllViews();
}
