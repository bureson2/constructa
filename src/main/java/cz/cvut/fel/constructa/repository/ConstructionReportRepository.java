package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * @param id the id
     * @return the list
     */
    List<ConstructionReport> findAllByProjectId(Long id, Sort sort);
}
