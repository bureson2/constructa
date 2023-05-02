package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.WorkReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;

/**
 * The interface Work report repository.
 */
@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
    /**
     * Find work reports by reporting employee id list.
     *
     * @param id   the id
     * @param sort the sort
     * @return the list
     */
    List<WorkReport> findWorkReportsByReportingEmployeeId(Long id, Sort sort);

    /**
     * Find today attendance list.
     *
     * @param id    the id
     * @param today the today
     * @return the list
     */
    @Query("SELECT wr FROM WorkReport wr WHERE wr.reportingEmployee.id = :id AND wr.timeFrom > :today ORDER BY wr.timeTo ASC" )
    List<WorkReport> findTodayAttendance(@Param("id") Long id, @Param("today") Date today);

    /**
     * Delete by reporting employee id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM WorkReport wr WHERE wr.reportingEmployee.id = :userId")
    void deleteByReportingEmployeeId(@Param("userId") Long userId);

}
