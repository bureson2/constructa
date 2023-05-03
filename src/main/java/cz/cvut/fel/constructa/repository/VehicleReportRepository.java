package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Sort;

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
    List<VehicleReport> findVehicleReportByVehicleId(Long vehicleId, Sort sort);

//    todo to test
    @Transactional
    @Modifying
    @Query("DELETE FROM VehicleReport vr WHERE vr.vehicle.id = :vehicleId")
    void deleteByVehicleId(@Param("vehicleId") Long vehicleId);
}
