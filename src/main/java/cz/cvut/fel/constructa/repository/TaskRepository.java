package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
