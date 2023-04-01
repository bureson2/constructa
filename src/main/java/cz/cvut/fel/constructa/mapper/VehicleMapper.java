package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class VehicleMapper {
    private final ModelMapper modelMapper;

    public VehicleDTO convertToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    public VehicleInputDTO convertToInputDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleInputDTO.class);
    }

    public Vehicle convertToEntity(VehicleRequest request) throws ParseException {
        return modelMapper.map(request, Vehicle.class);
    }
}
