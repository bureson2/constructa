package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/api/v1/vehicles")
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
    ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException;
    @DeleteMapping(value = "/reports/{reportId}")
    ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId);
}
