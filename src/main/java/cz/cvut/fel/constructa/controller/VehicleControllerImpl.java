package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleController;
import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The type Vehicle controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleControllerImpl implements VehicleController {
    /**
     * The Vehicle service.
     */
    private final VehicleService vehicleService;

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        return ResponseEntity.ok().body(
                vehicleService.getVehicles()
        );
    }

    /**
     * Gets input vehicles.
     *
     * @return the input vehicles
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleInputDTO>> getInputVehicles() {
        return ResponseEntity.ok().body(
                vehicleService.getInputVehicles()
        );
    }

    /**
     * Gets vehicle.
     *
     * @param vehicleId the vehicle id
     * @return the vehicle
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId) {
        VehicleDTO vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle != null) {
            return ResponseEntity.ok().body(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create vehicle response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                vehicleService.create(request)
        );
    }

    /**
     * Update vehicle response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                vehicleService.update(request)
        );
    }

    /**
     * Delete vehicle response entity.
     *
     * @param vehicleId the vehicle id
     * @return the response entity
     */
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
