package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByAssigneeId(Long id);
}
