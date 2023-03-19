package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleController {
    ResponseEntity<List<VehicleDTO>> getVehicles();

    ResponseEntity<VehicleDTO> getVehicle(Long vehicleId);

    public ResponseEntity<VehicleDTO> createVehicle(User newVehicle);

    ResponseEntity<VehicleDTO> updateVehicle(Vehicle vehicle, Long vehicleId);

    ResponseEntity<Void> deleteVehicle(Long id);
}
