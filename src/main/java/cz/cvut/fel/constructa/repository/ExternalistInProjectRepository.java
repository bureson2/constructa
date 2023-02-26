package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalistInProjectRepository extends JpaRepository<ExternalistInProject, Long> {
}
