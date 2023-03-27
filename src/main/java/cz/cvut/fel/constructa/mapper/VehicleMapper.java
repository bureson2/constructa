package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper {
    private final ModelMapper modelMapper;

    public VehicleDTO convertToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }
    public VehicleInputDTO convertToInputDto(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleInputDTO.class);
    }


}
