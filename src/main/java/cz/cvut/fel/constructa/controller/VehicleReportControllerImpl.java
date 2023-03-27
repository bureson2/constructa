package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleReportsController;
import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
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

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleReportControllerImpl implements VehicleReportsController {
    private final VehicleReportService vehicleReportService;
    private final VehicleReportMapper vehicleReportMapper;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleReportDTO>> getVehicleReports() {
        List<VehicleReport> vehicleReports = vehicleReportService.getVehicleReports();
        return ResponseEntity.ok().body(
                vehicleReports.stream()
                        .map(vehicleReportMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{carId}/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleReportDTO>> getVehicleReportsByVehicle(@PathVariable Long carId) {
        List<VehicleReport> vehicleReports = vehicleReportService.getVehicleReportsByVehicleId(carId);
        return ResponseEntity.ok().body(
                vehicleReports.stream()
                        .map(vehicleReportMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/reports/{vehicleReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> getVehicleReport(@PathVariable Long vehicleReportId) {
        Optional<VehicleReport> vehicleReportToReturn = vehicleReportService.getVehicleReportById(vehicleReportId);
        return vehicleReportToReturn.map(vehicleReport -> ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(vehicleReport)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> createVehicleReport(@RequestBody VehicleReportRequest request) throws ParseException {
        VehicleReport createdVehicleReport = vehicleReportService.create(request);
        return ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(createdVehicleReport)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReport vehicleReport) {
        VehicleReport vehicleReportToUpdate = vehicleReportService.update(vehicleReport);
        return ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(vehicleReportToUpdate));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/reports/{reportId}")
    public ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId) {
        vehicleReportService.deleteVehicleReport(reportId);
        return ResponseEntity.noContent().build();
    }


}
