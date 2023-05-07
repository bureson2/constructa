package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleReportsController;
import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.VehicleReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Vehicle report controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Vehicle reports")
@RequiredArgsConstructor
public class VehicleReportControllerImpl implements VehicleReportsController {
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * The Vehicle report service.
     */
    private final VehicleReportService vehicleReportService;

    /**
     * Has permission boolean.
     *
     * @param requiredRoles the required roles
     * @return the boolean
     */
    private boolean hasPermission(List<GrantedAuthority> requiredRoles){
        Authentication authentication = authenticationFacade.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().anyMatch(requiredRoles::contains);
    }

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
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_REPORTER")
        );

        if (hasPermission(requiredRoles)) {
            return ResponseEntity.ok().body(
                    vehicleReportService.update(request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_REPORTER")
        );

        if (hasPermission(requiredRoles)) {
            vehicleReportService.deleteVehicleReport(reportId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


}
