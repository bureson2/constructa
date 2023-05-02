package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Project repository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /**
     * Sets project manager to null by user id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("UPDATE Project pr SET pr.projectManager = null WHERE pr.projectManager.id = :userId")
    void setProjectManagerToNullByUserId(@Param("userId") Long userId);
}
