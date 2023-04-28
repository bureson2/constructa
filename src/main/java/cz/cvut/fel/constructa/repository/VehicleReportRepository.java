package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Vehicle report repository.
 */
@Repository
public interface VehicleReportRepository extends JpaRepository<VehicleReport, Long> {
    /**
     * Find vehicle report by vehicle id list.
     *
     * @param vehicleId the vehicle id
     * @return the list
     */
    List<VehicleReport> findVehicleReportByVehicleId(Long vehicleId);
}
