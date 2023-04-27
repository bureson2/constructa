package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Vehicle reports controller.
 */
@RequestMapping("/api/v1/vehicles")
public interface VehicleReportsController {
    /**
     * Gets vehicle reports.
     *
     * @return the vehicle reports
     */
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleReportDTO>> getVehicleReports();

    /**
     * Gets vehicle reports by vehicle.
     *
     * @param carId the car id
     * @return the vehicle reports by vehicle
     */
    @GetMapping(value = "/{carId}/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleReportDTO>> getVehicleReportsByVehicle(@PathVariable Long carId);

    /**
     * Gets vehicle report.
     *
     * @param vehicleReportId the vehicle report id
     * @return the vehicle report
     */
    @GetMapping(value = "/reports/{vehicleReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> getVehicleReport(@PathVariable Long vehicleReportId);

    /**
     * Create vehicle report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> createVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException;

    /**
     * Update vehicle report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException;

    /**
     * Delete vehicle report response entity.
     *
     * @param reportId the report id
     * @return the response entity
     */
    @DeleteMapping(value = "/reports/{reportId}")
    ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId);
}
