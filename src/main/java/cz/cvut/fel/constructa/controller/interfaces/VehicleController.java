package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface VehicleController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleDTO>> getVehicles();
    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleInputDTO>> getInputVehicles();
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody Vehicle newVehicle);
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> updateVehicle(@RequestBody Vehicle vehicle);
    @DeleteMapping(value = "/{vehicleId}")
    ResponseEntity<Void> deleteVehicle(@PathVariable Long id);
}
