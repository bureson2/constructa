package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReportRepository extends JpaRepository<VehicleReport, Long> {
}
