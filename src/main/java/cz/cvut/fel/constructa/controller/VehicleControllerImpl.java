package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleController;
import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.mapper.VehicleMapper;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.service.interfaces.VehicleService;
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
public class VehicleControllerImpl implements VehicleController {
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;
    private final VehicleReportMapper vehicleReportMapper;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok().body(
                vehicles.stream()
                        .map(vehicleMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    // TODO s filtrací

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleInputDTO>> getInputVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok().body(
                vehicles.stream()
                        .map(vehicleMapper::convertToInputDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId) {
        Optional<Vehicle> vehicleToReturn = vehicleService.getVehicleById(vehicleId);
        return vehicleToReturn.map(vehicle -> ResponseEntity.ok().body(
                vehicleMapper.convertToDto(vehicle)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody Vehicle newVehicle) {
        Vehicle createdVehicle = vehicleService.create(newVehicle);
        return ResponseEntity.ok().body(
                vehicleMapper.convertToDto(createdVehicle)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleToUpdate = vehicleService.update(vehicle);
        return ResponseEntity.ok().body(
                vehicleMapper.convertToDto(vehicleToUpdate));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleReportDTO>> getVehicleReports() {
        List<VehicleReport> vehicleReports = vehicleService.getVehicleReports();
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
        Optional<VehicleReport> vehicleReportToReturn = vehicleService.getVehicleReportById(vehicleReportId);
        return vehicleReportToReturn.map(vehicleReport -> ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(vehicleReport)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> createVehicleReport(@RequestBody VehicleReportRequest requet) throws ParseException {
        VehicleReport createdVehicleReport = vehicleService.create(requet);
        return ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(createdVehicleReport)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/reports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleReportDTO> updateVehicleReport(@RequestBody VehicleReport vehicleReport) {
        VehicleReport vehicleReportToUpdate = vehicleService.update(vehicleReport);
        return ResponseEntity.ok().body(
                vehicleReportMapper.convertToDto(vehicleReportToUpdate));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/reports/{reportId}")
    public ResponseEntity<Void> deleteVehicleReport(@PathVariable Long reportId) {
        vehicleService.deleteVehicleReport(reportId);
        return ResponseEntity.noContent().build();
    }
}
