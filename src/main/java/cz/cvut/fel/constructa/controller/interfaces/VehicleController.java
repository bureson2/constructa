package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.util.List;

public interface VehicleController {
    ResponseEntity<List<VehicleDTO>> getVehicles();

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleInputDTO>> getInputVehicles();

    ResponseEntity<VehicleDTO> getVehicle(Long vehicleId);

    public ResponseEntity<VehicleDTO> createVehicle(Vehicle newVehicle);

    ResponseEntity<VehicleDTO> updateVehicle(Vehicle vehicle);

    ResponseEntity<Void> deleteVehicle(Long id);

    ResponseEntity<List<VehicleReportDTO>> getVehicleReports();

    ResponseEntity<VehicleReportDTO> getVehicleReport(Long vehicleReportId);

    public ResponseEntity<VehicleReportDTO> createVehicleReport(VehicleReportRequest request) throws ParseException;

    ResponseEntity<VehicleReportDTO> updateVehicleReport(VehicleReport vehicle);

    ResponseEntity<Void> deleteVehicleReport(Long id);
}
