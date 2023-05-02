package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Task;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Sets assignee to null by user id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.assignee = null WHERE t.assignee.id = :userId")
    void setAssigneeToNullByUserId(@Param("userId") Long userId);

    /**
     * Sets author to null by user id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.author = null WHERE t.author.id = :userId")
    void setAuthorToNullByUserId(@Param("userId") Long userId);

}
