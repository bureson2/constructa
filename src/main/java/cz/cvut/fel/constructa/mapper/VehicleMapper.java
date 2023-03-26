package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDTO convertToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

}
