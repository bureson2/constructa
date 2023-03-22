package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleController {
    ResponseEntity<List<VehicleDTO>> getVehicles();

    ResponseEntity<VehicleDTO> getVehicle(Long vehicleId);

    public ResponseEntity<VehicleDTO> createVehicle(Vehicle newVehicle);

    ResponseEntity<VehicleDTO> updateVehicle(Vehicle vehicle);

    ResponseEntity<Void> deleteVehicle(Long id);

    ResponseEntity<List<VehicleReportDTO>> getVehicleReports();

    ResponseEntity<VehicleReportDTO> getVehicleReport(Long vehicleReportId);

    public ResponseEntity<VehicleReportDTO> createVehicleReport(VehicleReport newVehicleReport);

    ResponseEntity<VehicleReportDTO> updateVehicleReport(VehicleReport vehicle);

    ResponseEntity<Void> deleteVehicleReport(Long id);
}
