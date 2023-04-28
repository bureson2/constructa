package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleReportsController;
import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.service.interfaces.VehicleReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Vehicle report controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleReportControllerImpl implements VehicleReportsController {
    /**
     * The Vehicle report service.
     */
    private final VehicleReportService vehicleReportService;

    /**
     * Gets vehicle reports.
     *
     * @return the vehicle reports
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleReportDTO>> getVehicleReports() {
        return ResponseEntity.ok().body(
                vehicleReportService.getVehicleReports()
        );
    }

    /**
     * Gets vehicle reports by vehicle.
     *
     * @param carId the car id
     * @return the vehicle reports by vehicle
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{carId}/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleReportDTO>> getVehicleReportsByVehicle(@PathVariable Long carId) {
        return ResponseEntity.ok().body(
                vehicleReportService.getVehicleReportsByVehicleId(carId)
        );
    }

    /**
     * Gets vehicle report.
     *
     * @param vehicleReportId the vehicle report id
     * @return the vehicle report
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/reports/{vehicleReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> getVehicleReport(@PathVariable Long vehicleReportId) {
        VehicleReportDTO report = vehicleReportService.getVehicleReportById(vehicleReportId);
        if (report != null) {
            return ResponseEntity.ok().body(
                    report
            );
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create vehicle report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> createVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                vehicleReportService.create(request)
        );
    }

    /**
     * Update vehicle report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                vehicleReportService.update(request)
        );
    }

    /**
     * Delete vehicle report response entity.
     *
     * @param reportId the report id
     * @return the response entity
     */
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/reports/{reportId}")
    public ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId) {
        vehicleReportService.deleteVehicleReport(reportId);
        return ResponseEntity.noContent().build();
    }


}
