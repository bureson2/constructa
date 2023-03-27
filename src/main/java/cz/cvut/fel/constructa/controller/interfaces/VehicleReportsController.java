package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface VehicleReportsController {
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleReportDTO>> getVehicleReports();
    @GetMapping(value = "/{carId}/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleReportDTO>> getVehicleReportsByVehicle(@PathVariable Long carId);
    @GetMapping(value = "/reports/{vehicleReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> getVehicleReport(@PathVariable Long vehicleReportId);
    @PostMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> createVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException;
    @PutMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReport vehicleReport);
    @DeleteMapping(value = "/reports/{reportId}")
    ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId);
}
