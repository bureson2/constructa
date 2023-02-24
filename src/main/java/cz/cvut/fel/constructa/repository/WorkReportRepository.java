package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
}
