package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Vehicle controller.
 */
@RequestMapping("/api/v1/vehicles")
public interface VehicleController {
    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleDTO>> getVehicles();

    /**
     * Gets input vehicles.
     *
     * @return the input vehicles
     */
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleInputDTO>> getInputVehicles();

    /**
     * Gets vehicle.
     *
     * @param vehicleId the vehicle id
     * @return the vehicle
     */
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId);

    /**
     * Create vehicle response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleRequest request) throws ParseException;

    /**
     * Update vehicle response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleRequest request) throws ParseException;

    /**
     * Delete vehicle response entity.
     *
     * @param vehicleId the vehicle id
     * @return the response entity
     */
    @DeleteMapping(value = "/{vehicleId}")
    ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId);
}
