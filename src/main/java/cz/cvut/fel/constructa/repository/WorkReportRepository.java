package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The interface Work report repository.
 */
@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
    /**
     * Find work reports by reporting employee id list.
     *
     * @param id the id
     * @return the list
     */
    List<WorkReport> findWorkReportsByReportingEmployeeId(Long id);

    /**
     * Find today attendance list.
     *
     * @param id    the id
     * @param today the today
     * @return the list
     */
    @Query("SELECT wr FROM WorkReport wr WHERE wr.reportingEmployee.id = :id AND wr.timeFrom > :today")
    List<WorkReport> findTodayAttendance(@Param("id") Long id, @Param("today") Date today);
}
