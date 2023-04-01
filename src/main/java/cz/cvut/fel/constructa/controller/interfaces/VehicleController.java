package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface VehicleController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleDTO>> getVehicles();

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VehicleInputDTO>> getInputVehicles();

    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleRequest request) throws ParseException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleRequest request) throws ParseException;

    @DeleteMapping(value = "/{vehicleId}")
    ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId);
}
