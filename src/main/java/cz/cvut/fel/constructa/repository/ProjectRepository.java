package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Project repository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
