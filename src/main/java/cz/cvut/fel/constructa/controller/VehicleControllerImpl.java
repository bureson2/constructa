package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.VehicleController;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleControllerImpl implements VehicleController {
    @Override
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        return null;
    }

    @Override
    public ResponseEntity<VehicleDTO> getVehicle(Long vehicleId) {
        return null;
    }

    @Override
    public ResponseEntity<VehicleDTO> createVehicle(User newVehicle) {
        return null;
    }

    @Override
    public ResponseEntity<VehicleDTO> updateVehicle(Vehicle vehicle, Long vehicleId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(Long id) {
        return null;
    }
}
