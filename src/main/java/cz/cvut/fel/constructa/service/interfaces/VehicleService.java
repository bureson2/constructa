package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.model.Vehicle;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    VehicleDTO create(VehicleRequest request) throws ParseException;
    VehicleDTO getVehicleById(Long id);
    List<VehicleDTO> getVehicles();
    List<VehicleInputDTO> getInputVehicles();
    void deleteVehicle(Long id);
    VehicleDTO update(VehicleRequest request) throws ParseException;
}
