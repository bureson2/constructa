package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle create(Vehicle newVehicle);
    Optional<Vehicle> getVehicleById(Long id);
    List<Vehicle> getVehicles();
    void deleteVehicle(Long id);
    Vehicle update(Vehicle updatedVehicle);
}
