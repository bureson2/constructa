package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find task by assignee id list.
     *
     * @param id the id
     * @return the list
     */
    List<Task> findTaskByAssigneeId(Long id);
}
