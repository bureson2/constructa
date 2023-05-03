package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Sort;

/**
 * The interface Construction report repository.
 */
@Repository
public interface ConstructionReportRepository extends JpaRepository<ConstructionReport, Long> {
    /**
     * Find all by project id list.
     *
     * @param id   the id
     * @param sort the sort
     * @return the list
     */
    List<ConstructionReport> findAllByProjectId(Long id, Sort sort);

    /**
     * Sets executor to null by user id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConstructionReport cr SET cr.executor = null WHERE cr.executor.id = :userId")
    void setExecutorToNullByUserId(@Param("userId") Long userId);

    //    todo to test
    @Transactional
    @Modifying
    @Query("DELETE FROM ConstructionReport cr WHERE cr.project.id = :projectId")
    void deleteByprojectId(@Param("projectId") Long projectId);
}
