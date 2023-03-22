package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle create(Vehicle newVehicle);
    Optional<Vehicle> getVehicleById(Long id);
    List<Vehicle> getVehicles();
    void deleteVehicle(Long id);
    Vehicle update(Vehicle updatedVehicle);


    VehicleReport create(VehicleReport newVehicleReport);
    Optional<VehicleReport> getVehicleReportById(Long id);
    List<VehicleReport> getVehicleReports();
    void deleteVehicleReport(Long id);
    VehicleReport update(VehicleReport updatedVehicleReport);
}
