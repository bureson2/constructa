package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConstructionReportRepository extends JpaRepository<ConstructionReport, Long> {
//    List<ConstructionReport> findConstructionReportsByProjectId(Long id);
List<ConstructionReport> findAllByProjectId(Long id);
}
