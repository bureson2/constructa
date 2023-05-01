package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;


import java.util.List;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find task by assignee id list.
     *
     * @param id   the id
     * @param sort the sort
     * @return the list
     */
    List<Task> findTaskByAssigneeId(Long id, Sort sort);

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    @NotNull
    List<Task> findAll(@NotNull Sort sort);

}
