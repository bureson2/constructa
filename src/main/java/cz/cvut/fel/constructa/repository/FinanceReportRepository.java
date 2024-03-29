package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.FinanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Finance report repository.
 */
@Repository
public interface FinanceReportRepository extends JpaRepository<FinanceReport, Long> {
}
